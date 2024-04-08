const { update, getTrainer, addTrainer, deleteTrainer, getSubscribers, subscriberCount } = require('../controllers/trainer.js');
const { verifyToken } =  require('../verifyToken.js');
const express = require('express');
const cors = require('cors');
const router = express.Router();

router.use(cors());

router.put("/:id", addTrainer)

router.put("/update/:id", verifyToken, update)

router.delete("/:id", verifyToken, deleteTrainer)

router.put("/sub/:id", verifyToken, getSubscribers)

router.put("/subc/:id", verifyToken, subscriberCount);

router.get("/find/:id", getTrainer)

module.exports = router;