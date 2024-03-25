require('dotenv').config();
const express = require('express');
const mysql = require('mysql');
import userRoutes from "./routes/users.js";
import videoRoutes from "./routes/videos.js";
import commentRoutes from "./routes/comments.js";

const { Storage } = require('@google-cloud/storage');
const storage = new Storage({
  projectId: "My Project 15171",
  keyFilename: "green-mercury-414423-8f5b1eb3d98f.json",
});

const app = express();

const connect = () => {
  mongoose.connect(process.env.MONGODB).then(() => {
    console.log("Connected to Database");
  }).catch(err => {
    throw err;
  });
};

app.use("/api/users", userRoutes);

app.get("/", function (req, res) {
  res.sendFile(__dirname + "/index.html");
});

app.get("/video", async function(req, res) {
  const range = req.headers.range;
  if (!range) {
    return res.status(400).send("Requires Range header");
  }
  const bucket = storage.bucket(process.env.GCS_BUCKET);
  const remoteFile = bucket.file('file_example_MP4_1920_18MG.mp4');

  try {
    const [metadata] = await remoteFile.getMetadata();
    const videoSize = metadata.size;

    const CHUNK_SIZE = 10 ** 6; // 1MB
    const start = Number(range.replace(/\D/g, ""));
    const end = Math.min(start + CHUNK_SIZE, videoSize - 1);

    const contentLength = end - start + 1;
    const headers = {
      "Content-Range": `bytes ${start}-${end}/${videoSize}`,
      "Accept-Ranges": "bytes",
      "Content-Length": contentLength,
      "Content-Type": "video/mp4",
    };

    res.writeHead(206, headers);
    remoteFile.createReadStream({ start, end }).pipe(res);
  } catch (error) {
    console.error("Error streaming video:", error);
    res.status(500).send("Internal Server Error");
  }
});

app.listen(8000, function () {
  console.log("Listening on port 8000!");
});
