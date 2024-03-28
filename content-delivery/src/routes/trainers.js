const { update, getTrainer, addTrainer, deleteTrainer } = require('../controllers/trainer.js');
const { verifyToken } =  require('../verifyToken.js');
const express = require('express');
const router = express.Router();

router.put("/:id", addTrainer)

router.put("/update/:id", verifyToken, update)

router.delete("/:id", verifyToken, deleteTrainer)

router.get("/find/:id", getTrainer)

module.exports = router;