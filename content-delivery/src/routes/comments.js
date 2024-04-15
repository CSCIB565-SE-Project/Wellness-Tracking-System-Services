const express = require('express');
const { addComment, deleteComment, getComment } = require("../controllers/comment.js");
const { verifyToken } = require("../verifyToken.js");
const cors = require('cors');
const router = express.Router();

router.use(cors());
router.post("/", verifyToken, addComment);
router.delete("/:id", verifyToken, deleteComment)
router.get("/:videoId", getComment)

module.exports = router;