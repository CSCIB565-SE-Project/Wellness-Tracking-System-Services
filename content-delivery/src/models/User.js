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
        userLName: {
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
            type: [String]
        }
    }
);

module.exports = mongoose.model("User", UserSchema);
