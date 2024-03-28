const mongoose = require('mongoose');

const TrainerSchema = new mongoose.Schema(
    {
        userId: {
            type: String,
            required: true
        },
        img: {
            type: String,
        },
        subscribers: {
            type: Number,
            default: 0
        },
        subscribedUsers: {
            type: [String]
        }
    }
);

module.exports = mongoose.model("Trainer", TrainerSchema);
