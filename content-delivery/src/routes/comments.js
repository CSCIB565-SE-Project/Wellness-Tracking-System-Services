const express = require('express');
const { addComment, deleteComment, getComment } = require("../controllers/comment.js");
const { verifyToken } = require("../verifyToken.js");

const router = express.Router();

router.post("/", verifyToken, addComment);
router.delete("/:id", verifyToken, deleteComment)
router.get("/:videoId", getComment)

module.exports = router;