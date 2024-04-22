const { createError } = require("../error");
const Trainer = require("../models/Trainer.js");


const addTrainer = async (req, res, next) => {
    try {
      const newTrainer = new Trainer({ ...req.body }); 
      await newTrainer.save();
      res.status(200).send("Trainer Onboarded to database!");
    } catch (err) {
      next(err);
    }
  };


const getSubscribers = async(req, res, next) => {
    try{
        const trainer = await Trainer.find({userId: req.params.id});
        const subscribers = trainer[0].subscribedUsers;
        res.status(200).json(subscribers);
    }
    catch(err){
        next(err);
    }
};

const subscriberCount = async(req, res, next) => {
    try{
        const trainer = await Trainer.findOne({userId: req.params.id});
        const subCount = trainer.subscribers;
        res.status(200).json(subCount);
    }
    catch(err){
        next(err);
    }
};

const update = async(req, res, next) => {
    if(req.params.id === req.user.id){
        try{
            const updatedTrainer  = await Trainer.findByIdAndUpdate(req.params.id, {
                $set: req.body
            },
            { new: true }
            );
            res.status(200).json(updatedTrainer);
        }
        catch(err){
            next(err);
        }
    }
    else{
        return next(createError(403, "Unauthorized"));
    }
};

const deleteTrainer = async(req, res, next) => {
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

const getTrainer = async(req, res, next) => {
    try{
        const trainer = await Trainer.findById(req.params.id);
        res.status(200).json(trainer);
    }
    catch(err){
        next(err);
    }
}

module.exports = { addTrainer, update, deleteTrainer, getTrainer, getSubscribers, subscriberCount };