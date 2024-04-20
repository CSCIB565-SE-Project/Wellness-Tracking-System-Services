var express = require('express');
var authRouter = require('./routes/auth');
var requestRouter = require('./routes/request');
var app = express();

app.options('*', function(req, res, next){
  res.header("Access-Control-Allow-Origin", "http://localhost:3000");
  res.header("Access-Control-Allow-Credentials", "true")
  res.header("Access-Control-Allow-Headers", ['X-Requested-With',
  'content-type','credentials']);
  res.header('Access-Control-Allow-Methods', 'GET, POST');
  res.status(200);
  next()
})

app.use('/auth', authRouter);
app.use('/request', requestRouter);

app.listen(8000, function () {
    console.log("Listening on port 8000!");
  });