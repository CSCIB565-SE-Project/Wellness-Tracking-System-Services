const mongoose = require('mongoose');

const UserSchema = new mongoose.Schema(
    {
        userId: {
            type: String,
            required: true
        },
        img: {
            type: String,
        },
        subscribedTrainers: {
            type: [String]
        }
    }
);

module.exports = mongoose.model("User", UserSchema);
