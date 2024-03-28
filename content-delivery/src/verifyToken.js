const jwt = require('jsonwebtoken');
const { createError } = require('./error');

const verifyToken = (req, res, next) => {
    const token = req.cookies.session_token;
    if(!token){
        return next(createError(401, "Not Authenticated"));
    }

    jwt.verify(token, tempToken, (err, user) => {
        if(err){
            return next(createError(403, "Invalid Token"));
        }
        else{
            req.user = user;
            next();
        }
    });
};

module.exports = { verifyToken };