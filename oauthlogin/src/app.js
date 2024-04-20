var express = require('express');
var authRouter = require('./routes/auth');
var requestRouter = require('./routes/request');
var app = express();

app.use('/auth', authRouter);
app.use('/request', requestRouter);

app.listen(8000, function () {
    console.log("Listening on port 8000!");
  });