const { addWorkoutPlan, deleteWorkoutPlan, parameterizedSearch, getWorkoutPlan, getWorkoutPlanByTrainer, updateWorkoutPlan, like, dislike } = require('../controllers/workoutplan');
const { verifyToken } = require('../verifyToken');
const express = require('express');
const router = express.Router();

router.post("/", verifyToken, addWorkoutPlan);
router.put("/:id", verifyToken, updateWorkoutPlan);
router.delete("/:id", verifyToken, deleteWorkoutPlan);
router.get("/find/:id", verifyToken, parameterizedSearch);
router.put("/view/:id", verifyToken, getWorkoutPlanByTrainer);
router.get("/like/:wplanId", verifyToken, like)
router.get("/unlike/:wplanId", verifyToken, dislike)

module.exports = router;