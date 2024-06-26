const mongoose = require('mongoose');

const VideoSchema = new mongoose.Schema(
    {
        trainerId: {
            type: String,
            required: true
        },
        workOutPlanId: {
            type: String,
            required: true
        },
        title: {
            type: String,
            required: true,
        },
        description: {
            type: String,
            required: true,
        },
        imgUrl: {
            type: String,
        },
        videoUrl: {
            type: String,
            required: true
        },
        modeOfInstruction: {
            type: String,
            required: true
        },
        typeOfWorkout: {
            type: String,
            required: true
        },
        caloriesBurnt: {
            type: String,
            required: true
        },
        views: {
            type: Number,
            default: 0
        },
        tags: {
            type: [String],
            default: []
        },
        likes: {
            type: [String],
            default: []
        },
        dislikes: {
            type: [String],
            default: []
        },
        isApproved: {
            type: Boolean,
            default: false
        }
    },
    { timestamps: true }
);

module.exports = mongoose.model("Video", VideoSchema);