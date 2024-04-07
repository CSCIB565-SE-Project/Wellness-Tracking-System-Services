const { query } = require("express");
const { createError } = require("../error.js");
const Trainer = require("../models/Trainer.js");
const User = require("../models/User.js");
const WorkoutPlan = require("../models/WorkoutPlan.js");
const Video = require("../models/Video.js");

const addWorkoutPlan = async(req, res, next) => {
    console.log(req.body);
    const newWorkoutPlan = new WorkoutPlan({ trainerId: req.body.trainerId, ...req.body });
    try{
        const savedWorkoutPlan = await newWorkoutPlan.save();
        res.status(200).json(savedWorkoutPlan);
    }
    catch(err){
        next(err);
    }
}

const deleteWorkoutPlan = async(req, res, next) => {
    try{
        const wplan = await WorkoutPlan.findById(req.params.id);
        if(!wplan){
            return next(createError(404, "Not Found"));
        }
        if(req.body.userId == wplan.trainerId){
            await Video.deleteMany({workOutPlanId: req.params.id});
            await WorkoutPlan.findByIdAndDelete(req.params.id);
            res.status(200).json("Deleted");
        }
        else{
            return next(createError(403, "Unauthorized"));
        }
    }
    catch(err){
        next(err);
    }
}

const updateWorkoutPlan = async(req, res, next) => {
    try{
        const wplan = await WorkoutPlan.findById(req.params.id);
        if(!wplan){
            return next(createError(404, "Not Found"));
        }
        if(req.user.id === wplan.trainerId){
            const updatedWorkoutPlan = await WorkoutPlan.findByIdAndUpdate(req.params.id, {
                $set: req.body
            },
            { new: true}
            );
            res.status(200).json(updatedWorkoutPlan);
        }
        else{
            return next(createError(403, "Unauthorized"));
        }
    }
    catch(err){
        next(err);
    }
}

const getWorkoutPlan = async(req, res, next) => {
    try{
        const wplan = await WorkoutPlan.findById(req.params.id);
        const creator = wplan.trainerId;
        const trainer = await Trainer.findById(creator);
        if(req.params.userId in trainer.subscribedUsers){
            res.status(200).json(wplan);
        }
        else{
            return next(createError(401, "Unsubscribed"));
        }       
    }
    catch(err){
        next(err);
    }
}

const fetchCreatedWorkoutPlan = async(req, res, next) => {
    try{
        const trainer = await Trainer.find({userId: req.params.id})
        const list = await WorkoutPlan.find({trainerId: trainer[0].userId});
        res.status(200).json(list.flat().sort((a,b) => b.createdAt - a.createdAt));
    }
    catch(err){
        next(err);
    }
}

const getWorkoutPlanByTrainer = async(req, res, next) => {
    try{
        const trainer = await Trainer.findById(req.params.id)
        if(req.params.userId in trainer.subscribedUsers){
            const list = await WorkoutPlan.find({trainerId: trainer.userId});
            res.status(200).json(list.flat().sort((a,b) => b.createdAt - a.createdAt));
        }
        else{
            return next(createError(401, "Unsubscribed"));
        }
    }
    catch(err){
        next(err);
    }
}

const parameterizedSearch = async (req, res, next) => {
    const { q, typeOfWorkout } = req.query;
    const userId = req.user.id;
    const queryConditions = [];
    
    if (q) {
        queryConditions.push({
            $or: [
                { title: { $regex: q, $options: "i" } },
                { description: { $regex: q, $options: "i" } }
            ]
        });
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
        const wplans = await WorkoutPlan.find(query).limit(40);
        res.status(200).json(wplans);
    } catch (err) {
        next(err);
    }
}


const like = async(req, res, next) => {
    const id = req.user.id;
    const wplanId = req.params.workoutPlanId;
    try{
        await WorkoutPlan.findByIdAndUpdate(wplanId, {
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
    const id = req.user.id;
    const wplanId = req.params.workoutPlanId;
    try{
        await WorkoutPlan.findByIdAndUpdate(wplanId, {
            $addToSet:{dislikes:id},
            $pull:{likes:id}
        });
        res.status(200).json("Disliked");
    }
    catch(err){
        next(err);
    }
}

module.exports = { addWorkoutPlan, deleteWorkoutPlan, updateWorkoutPlan, getWorkoutPlan, fetchCreatedWorkoutPlan, getWorkoutPlanByTrainer, parameterizedSearch, like, dislike };