const { query } = require("express");
const { createError } = require("../error.js");
const Trainer = require("../models/Trainer.js");
const Video = require("../models/Video.js");
const WorkoutPlan = require("../models/WorkoutPlan.js");
const User = require("../models/User.js");
const { deleteVideoFromBlob } = require("../utils/deleteBlob.js");

const addVideo = async(req, res, next) => {
    const newVideo = new Video({ trainerId: req.body.trainerId, ...req.body });
    try{
        const savedVideo = await newVideo.save()
        const updatedWorkoutPlan = await WorkoutPlan.findByIdAndUpdate(req.query.workoutPlanId, {
            $addToSet: {videoIds:savedVideo.id}
        },
        { new: true }
        );
        res.status(200).json(savedVideo);
    }
    catch(err){
        console.log(err);
        next(err);
    }
}

const deleteManyVideo = async(planId) => {
    try{
        const videos = await Video.find({workoutPlanId: planId});
        for(var video in videos){
            tempVideoURL = video.videoUrl;
            try{
                deleteVideoFromBlob(tempVideoURL);
            } catch(error) {
                throw error;
            }
        }
        await Video.deleteMany({workOutPlanId: planId});
        return true;
    }
    catch(error){
        console.log(error);
        return false;
    }
}

const deleteVideo = async(req, res, next) => {
    try{
        const video = await Video.findById(req.params.id);
        if(!video){
            return next(createError(404, "Not Found"));
        }
        if(req.body.userId === video.trainerId){
            const updatedWorkoutPlan = await WorkoutPlan.findByIdAndUpdate(req.query.workoutPlanId, {
                $pull: {videoIds: video.id}
            },
            { new: true }
            );
            const checkRes = deleteVideoFromBlob(video.videoUrl);
            if(checkRes){
                await Video.findByIdAndDelete(req.params.id);
                res.status(200).json("Deleted");
            }
            else{
                throw new Error("Error Deleting Video");
            }       
        }
        else{
            return next(createError(403, "Unauthorized"));
        }
    }
    catch(err){
        next(err);
    }
}

const updateVideo = async(req, res, next) => {
    try{
        const video = await Video.findById(req.params.id);
        if(!video){
            return next(createError(404, "Not Found"));
        }
        if(req.headers.id === video.trainerId){
            const updatedVideo = await Video.findByIdAndUpdate(req.params.id, {
                $set: req.body
            },
            { new: true}
            );
            res.status(200).json(updatedVideo);
        }
        else{
            return next(createError(403, "Unauthorized"));
        }
    }
    catch(err){
        next(err);
    }
}

const getVideoForTrainer = async(req, res, next) => {
    try{
        const video = await Video.findById(req.params.id);
        if(!video){
            return next(createError(404, "Not Found"));
        }
        if(req.headers.id === video.trainerId){
            res.status(200).json(video);
        }
        else{
            return next(createError(401, "Unsubscribed"));
        }
        
    }
    catch(err){
        next(err);
    }
}

const getVideo = async(req, res, next) => {
    try{
        const video = await Video.findById(req.params.id);
        const videoCreator = video.trainerId;
        const trainer = await Trainer.findById(videoCreator);
        if(req.params.userId in trainer.subscribedUsers){
            res.status(200).json(video);
        }
        else{
            return next(createError(401, "Unsubscribed"));
        }
        
    }
    catch(err){
        next(err);
    }
}

const addView = async(req, res, next) => {
    try{
        await Video.findByIdAndUpdate(req.params.id, {
            $inc: {views:1}
        });
        res.status(200).json("View Added");
    }
    catch(err){
        next(err);
    }
}

const random = async(req, res, next) => {
    try{
        const videos = await Video.aggregate([{$sample: {size: 10}}]);
        res.status(200).json(videos);
    }
    catch(err){
        next(err);
    }
}

const trend = async(req, res, next) => {
    try{
        const creator = req.headers.id;
        const trainer = await Trainer.findById(creator);
        if(req.params.userId in trainer.subscribedUsers){
            const videos = Video.find({trainerId: trainer}).sort({views:-1});
            res.status(200).json(videos);
        }
        else{
            return next(createError(401, "Unsubscribed"));
        }   
    }
    catch(err){
        next(err);
    }
}

const sub = async(req, res, next) => {
    try{
        const user = await User.findById(req.headers.id)
        const trainers = user.subscribedTrainers;
        const list = await Promise.all(
            trainers.map((trainer) => {
                return Video.find({trainerId: trainer});
            })
        );
        res.status(200).json(list.flat().sort((a,b) => b.createdAt - a.createdAt));
    }
    catch(err){
        next(err);
    }
}

const getByTag = async (req, res, next) => {
    const tags = req.query.tags.split(",");
    try {
        const user = await User.findById(req.headers.id);
        const trainers = user.subscribedTrainers;
        const promises = trainers.map(trainer => {
            return Video.find({ tags: { $in: tags }, trainerId: trainer });
        });
        const videosArray = await Promise.all(promises);;
        const videos = videosArray.flat().slice(0, 20);
        res.status(200).json(videos);
    } catch (err) {
        next(err);
    }
}

const getByWorkout = async(req, res, next) => {
    try{
        const videos = await Video.find({ workOutPlanId: req.params.id });
        res.status(200).json(videos);
    } catch(err) {
        next(err);
    }
}

const search = async(req, res, next) => {
    const query = req.query.q;
    try{
        const videos = await Video.find({title:{$regex: query, $options: "i"}}).limit(40);
        res.status(200).json(videos);
    }
    catch(err){
        next(err);
    }
}

const parameterizedSearch = async (req, res, next) => {
    const { q, modeOfInstruction, typeOfWorkout } = req.query;
    const userId = req.headers.id;
    const queryConditions = [];
    
    if (q) {
        queryConditions.push({
            $or: [
                { title: { $regex: q, $options: "i" } },
                { description: { $regex: q, $options: "i" } },
                { tags: { $regex: q, $options: "i" } }
            ]
        });
    }
    
    if (modeOfInstruction) {
        queryConditions.push({ modeOfInstruction });
    }
    
    if (typeOfWorkout) {
        queryConditions.push({ typeOfWorkout });
    }
    
    try {
        const user = await User.findById(userId)
        const trainers = user.subscribedTrainers;
        if(trainers.length > 0){
            queryConditions.push({ trainerId: { $in: trainers } });
        }
        const query = queryConditions.length > 0 ? { $and: queryConditions } : {};
        const videos = await Video.find(query).limit(40);
        res.status(200).json(videos);
    } catch (err) {
        next(err);
    }
}

const getUnapprovedContent = async(req, res, next) => {
    try{
        const videos = await Video.find({trainerId: req.params.id, isApproved: false});
        res.status(200).json(videos);
    }
    catch(err){
        next(err);
    }
}


const like = async(req, res, next) => {
    const id = req.headers.id;
    const videoId = req.params.videoId;
    try{
        await Video.findByIdAndUpdate(videoId, {
            $addToSet:{likes:id},
            $pull:{dislikes:id}
        });
        res.status(200).json("Liked");
    }
    catch(err){
        next(err);
    }
}

const dislike = async(req, res, next) => {
    const id = req.headers.id;
    const videoId = req.params.videoId;
    try{
        await Video.findByIdAndUpdate(videoId, {
            $addToSet:{dislikes:id},
            $pull:{likes:id}
        });
        res.status(200).json("Disliked");
    }
    catch(err){
        next(err);
    }
}

module.exports = { addVideo, deleteVideo, deleteManyVideo, updateVideo, getVideo, getByTag, addView, random, trend, sub, search, parameterizedSearch, like, dislike, getUnapprovedContent, getByWorkout, getVideoForTrainer };