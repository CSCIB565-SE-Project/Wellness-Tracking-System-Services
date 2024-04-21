const { createError } = require("../error");
const User = require("../models/User.js");
const Trainer = require("../models/Trainer.js");

const addUser = async (req, res, next) => {
    try {
      const newUser = new User({ ...req.body }); 
      await newUser.save();
      res.status(200).send("User Onboarded to CDN database!");
    } catch (err) {
      next(err);
    }
  };

const update = async(req, res, next) => {
    if(req.params.id === req.user.id){
        try{
            const updatedUser  = await User.findByIdAndUpdate(req.params.id, {
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

const deleteUser = async(req, res, next) => {
    if(req.params.id === req.user.id){
        try{
            await User.findByIdAndDelete(req.params.id);
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

const getUser = async(req, res, next) => {
    try{
        const user = await User.findById(req.params.id);
        res.status(200).json(user);
    }
    catch(err){
        next(err);
    }
}

const subscribe = async(req, res, next) => {
    try{
        await User.findOneAndUpdate({ userId: req.body.id }, 
            { $push: { subscribedTrainers:req.params.id } }
        );
        await Trainer.findOneAndUpdate({ userId: req.params.id }, 
            { $push: { subscribedUsers:req.body.id } }
        );
        await Trainer.findOneAndUpdate({ userId: req.params.id }, 
            { $inc: { subscribers: 1 } }
        );
        res.status(200).json("Subscribed");
    }   
    catch(err){
        next(err);
    }
}

const getSubscribers = async(req, res, next) => {
    try{
        const user = await User.findOne( { userId: req.params.id } );
        let subscribedTrainers = user.subscribedTrainers;
        if(!subscribedTrainers){
            subscribedTrainers = [];
        }
        res.status(200).json(subscribedTrainers);
    } 
    catch(err){
        next(err);
    }
}

const unsubscribe = async(req, res, next) => {
    try{
        await User.findByIdAndUpdate(req.user.id, {
            $pull: { subscribedTrainers: req.params.id }
        });
        await Trainer.findByIdAndUpdate(req.params.id, {
            $pull: { subscribedUsers: req.user.id }
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

module.exports = { addUser, deleteUser, getUser, subscribe, unsubscribe, update, getSubscribers };