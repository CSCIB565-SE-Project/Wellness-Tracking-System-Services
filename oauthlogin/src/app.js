var express = require('express');
const cors = require('cors');
var authRouter = require('./routes/auth');
var requestRouter = require('./routes/request');
var app = express();

app.use(cors());
app.use('/auth', authRouter);
app.use('/request', requestRouter);

app.listen(8000, function () {
    console.log("Listening on port 8000!");
  });