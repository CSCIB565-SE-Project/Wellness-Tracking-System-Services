const mongoose = require('mongoose');

const UserSchema = new mongoose.Schema(
    {
        userId: {
            type: String,
            required: true
        },
        userEmail: {
            type: String,
            required: true
        },
        userFname: {
            type: String,
            required: true
        },
        userLname: {
            type: String,
            required: true
        },
        userDoB: {
            type: Date
        },
        userGender: {
            type: String
        },
        img: {
            type: String,
        },
        subscribedTrainers: {
            type: [String],
            default: []
        }
    }
);

module.exports = mongoose.model("User", UserSchema);
