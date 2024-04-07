const jwt = require('jsonwebtoken');
const { createError } = require('./error');

const verifyToken = (req, res, next) => {
    const token = req.headers.authorization && req.headers.authorization.split(' ')[1];;
    user = req.body.trainerId;
    if(!token){
        return next(createError(401, "Not Authenticated"));
    }
    tempToken = Buffer.from("357638792F423F4428472B4B6250655368566D597133743677397A2443264629", 'base64');
    jwt.verify(token, tempToken, (err, user) => {
        if(err){
            console.log(err)
            return next(createError(403, "Invalid Token"));
        }
        else{
            req.user = user;
            next();
        }
    });
};

module.exports = { verifyToken };