require('dotenv').config();
const express = require("express");
const mongoose = require("mongoose")
const userRoutes = require("./routes/users.js");
const trainerRoutes = require("./routes/trainers.js");
const videoRoutes = require("./routes/videos.js");
const commentRoutes = require("./routes/comments.js");
const workoutplanRoutes = require("./routes/workoutplans.js")
//const cookieParser = require("cookie-parser");

const app = express();

const connect = () => {
  mongoose
    .connect(process.env.MONGODB)
    .then(() => {
      console.log("Connected to DB");
    })
    .catch((err) => {
      throw err;
    });
};

//app.use(cookieParser())
app.use(express.json());
app.use("/api/trainers", trainerRoutes);
app.use("/api/users", userRoutes);
app.use("/api/videos", videoRoutes);
app.use("/api/comments", commentRoutes);
app.use("/api/workoutplan", workoutplanRoutes);


app.use((err, req, res, next) => {
  const status = err.status || 500;
  const message = err.message || "Something went wrong!";
  return res.status(status).json({
    success: false,
    status,
    message,
  });
});

app.get("/", function (req, res) {
  res.sendFile(__dirname + "/index.html");
});

app.listen(8000, function () {
  connect();
  console.log("Listening on port 8000!");
});
