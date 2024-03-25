import { addVideo, addView, deleteVideo, getByTag, getVideo, random, search, sub, trend, updateVideo } from '../controllers/video';
import { verifyToken } from '../verifyToken';
const express = require('express');
const router = express.Router();

router.post("/", verifyToken, addVideo);
router.put("/:id", verifyToken, updateVideo);
router.delete("/:id", verifyToken, deleteVideo);
router.get("/find/:id", getVideo);
router.put("/view/:id", addView);
router.get("/trend", trend);
router.put("/random", random);
router.put("/sub", verifyToken, sub);
router.put("/tags", verifyToken, getByTag);
router.put("/search", verifyToken, search);

export default router;