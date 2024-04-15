const { createError } = require("../error");
const Comment = require("../models/Comment.js");
const Video = require("../models/Video.js");
const addComment = async(req, res, next) => {
    const newComment = new Comment({...req.body, userId: req.headers.id});
    try{
        const savedComment = await newComment.save();
        res.status(200).send(savedComment);
    }
    catch(err){
        next(err);
    }
}

const deleteComment = async(req, res, next) => {
    try{
        const comment = await Comment.findById(req.params.id);
        const video = await Video.findById(req.params.id);
        if(req.user.id === comment.userId || req.user.id === video.userId){
            await Comment.findByIdAndDelete(req.params.id);
            res.status(200).json("Comment Deleted");
        }
        else{
            return next(createError(403, "Unauthorized"));
        }
    }
    catch(err){
        next(err);
    }
}

const getComment = async(req, res, next) => {
    try{
        const comments = await Comment.find({videoId: req.params.videoId});
        res.status(200).json(comments);
    }
    catch(err){
        next(err);
    }
}

module.exports = { addComment, deleteComment, getComment };