const { update, getUser, subscribe, unsubscribe, addUser, deleteUser } = require('../controllers/user.js');
const { verifyToken } = require('../verifyToken.js');
const express = require('express');
const router = express.Router();

router.put("/:id", addUser);
router.put("/update/:id", verifyToken, update);
router.delete("/:id", verifyToken, deleteUser);
router.get("/find/:id", getUser);
router.get("/sub/:id", verifyToken, subscribe);
router.get("/unsub/:id", verifyToken, unsubscribe);

module.exports = router;