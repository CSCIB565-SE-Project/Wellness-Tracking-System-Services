import mongoose from 'mongoose';

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

export default mongoose.model("Trainer", TrainerSchema);