const { addVideo, addView, deleteVideo, getByTag, parameterizedSearch, getVideo, random, search, sub, trend, updateVideo, like, dislike, getUnapprovedContent, getByWorkout, getVideoForTrainer } = require('../controllers/video');
const { streamVideo } = require('../utils/stream');
const { verifyToken } = require('../verifyToken');
const cors = require('cors');
const express = require('express');
const router = express.Router();

router.use(cors());
router.post("/add/:id", verifyToken, addVideo);
router.put("/:id", verifyToken, updateVideo);
router.delete("delete/:id", verifyToken, deleteVideo);
router.get("/find/:id", verifyToken, getVideo);
router.get("/play", streamVideo);
router.put("/view/:id", verifyToken, addView);
router.get("/trend", verifyToken, trend);
router.get("/trainer/get/:id", verifyToken, getVideoForTrainer);
router.put("/random", verifyToken, random);
router.put("/sub", verifyToken, sub);
router.put("/tags", verifyToken, getByTag);
router.get("/unapproved/:id", verifyToken, getUnapprovedContent);
router.get("/get/:id", verifyToken, getByWorkout);
router.put("/search", verifyToken, search);
router.get("/like/:videoId", verifyToken, like)
router.get("/unlike/:videoId", verifyToken, dislike)

module.exports = router;