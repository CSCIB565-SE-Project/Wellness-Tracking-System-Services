const { addWorkoutPlan, deleteWorkoutPlan, parameterizedSearch, getWorkoutPlan, getWorkoutPlanByTrainer, fetchCreatedWorkoutPlan, updateWorkoutPlan, like, dislike, viewWorkoutPlan } = require('../controllers/workoutplan');
const { verifyToken } = require('../verifyToken');
const express = require('express');
const cors = require('cors');
const router = express.Router();
// const corsOptions = {
//     AccessControlAllowOrigin: '*',
//     origin: 'http://localhost:3000',
//     methods: 'GET,HEAD,PUT,PATCH,POST,DELETE'
//   }

router.use(cors());
router.post("/add", verifyToken, addWorkoutPlan);
router.put("/update/:id", verifyToken, updateWorkoutPlan);
router.delete("/delete/:id", verifyToken, deleteWorkoutPlan);
router.get("/find/:id", verifyToken, parameterizedSearch);
router.put("/view/:id", verifyToken, getWorkoutPlanByTrainer);
router.put("/fetch/:id", verifyToken, fetchCreatedWorkoutPlan);
router.get("/view/:id", verifyToken, viewWorkoutPlan);
router.get("/like/:wplanId", verifyToken, like)
router.get("/unlike/:wplanId", verifyToken, dislike)

module.exports = router;