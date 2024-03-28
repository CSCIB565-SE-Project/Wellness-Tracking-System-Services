const { addVideo, addView, deleteVideo, getByTag, parameterizedSearch, getVideo, random, search, sub, trend, updateVideo, like, dislike } = require('../controllers/video');
const { streamVideo } = require('../utils/stream');
const { verifyToken } = require('../verifyToken');
const express = require('express');
const router = express.Router();

router.post("/", verifyToken, addVideo);
router.put("/:id", verifyToken, updateVideo);
router.delete("/:id", verifyToken, deleteVideo);
router.get("/find/:id", verifyToken, getVideo);
router.get("/play", streamVideo);
router.put("/view/:id", verifyToken, addView);
router.get("/trend", verifyToken, trend);
router.put("/random", verifyToken, random);
router.put("/sub", verifyToken, sub);
router.put("/tags", verifyToken, getByTag);
router.put("/search", verifyToken, search);
router.get("/like/:videoId", verifyToken, like)
router.get("/unlike/:videoId", verifyToken, dislike)

module.exports = router;