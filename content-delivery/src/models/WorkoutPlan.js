const mongoose = require('mongoose');

const WorkoutPlanSchema = new mongoose.Schema(
    {
        trainerId: {
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
        typeOfWorkout: {
            type: String,
            required: true,
        },
        videoIds: {
            type: String,
            required: true
        },
        likes: {
            type: [String],
            default: []
        },
        dislikes: {
            type: [String],
            default: []
        }
    },
    { timestamps: true }
);

module.exports = mongoose.model("WorkoutPlan", WorkoutPlanSchema);