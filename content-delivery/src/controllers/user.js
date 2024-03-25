import { createError } from "../error";
import Trainer from "../models/User.js";

export const update = async(req, res, next) => {
    if(req.params.id === req.user.id){
        try{
            const updatedUser  = await Trainer.findByIdAndUpdate(req.params.id, {
                $set: req.body
            },
            { new: true }
            );
            res.status(200).json(updatedUser);
        }
        catch(err){
            next(err);
        }
    }
    else{
        return next(createError(403, "Unauthorized"));
    }
};

export const deleteUser = async(req, res, next) => {
    if(req.params.id === req.user.id){
        try{
            await Trainer.findByIdAndDelete(req.params.id);
            res.status(200).json("User Deleted");
        }
        catch(err){
            next(err);
        }
    }
    else{
        return next(createError(403, "Unauthorized"));
    }
}

export const getUser = async(req, res, next) => {
    try{
        const user = await Trainer.findById(req.params.id);
        res.status(200).json(user);
    }
    catch(err){
        next(err);
    }
}

export const subscribe = async(req, res, next) => {
    try{
        await Trainer.findByIdAndUpdate(req.user.id, {
            $push:{subscribedUsers:req.params.id}
        });
        await Trainer.findByIdAndUpdate(req.params.id, {
            $inc: { subscribers: 1}
        });
        res.status(200).json("Subscribed");
    }   
    catch(err){
        next(err);
    }
}

export const unsubscribe = async(req, res, next) => {
    try{
        await Trainer.findByIdAndUpdate(req.user.id, {
            $pull: { subscribedUsers: req.params.id }
        });
        await Trainer.findByIdAndUpdate(req.params.id, {
            $inc: { subscribers: -1 }
        });
        res.status(200).json("Unsubscribed");
    }   
    catch(err){
        next(err);
    }
}

export const like = async(req, res, next) => {
    const id = req.user.id;
    const videoId = req.user.videoId;
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

export const dislike = async(req, res, next) => {
    const id = req.user.id;
    const videoId = req.user.videoId;
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

