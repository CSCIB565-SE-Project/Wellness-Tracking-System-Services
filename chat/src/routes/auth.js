//routes/auth.js backend nodejs
const express = require('express');

const { login, verifyAndGenerateToken } = require('../controllers/auth.js');

const router = express.Router();

router.post('/verifyToken', verifyAndGenerateToken);

module.exports = router;