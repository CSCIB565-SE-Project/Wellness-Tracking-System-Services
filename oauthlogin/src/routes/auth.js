var express = require('express');
var router = express.Router();
const dotenv = require('dotenv');
const passport = require('passport');
dotenv.config();
const {OAuth2Client} = require('google-auth-library');

async function getUserData(access_token){
    const response = await fetch(`https://www.googleapis.com/oauth2/v3/userinfo?access_token=${access_token}`);
    const data = await response.json();
    return data;
}

router.get('/google', async function(req, res, next){
    const code = req.query.code;
    try{
        const redirectUrl = 'http://localhost:8000/auth/google';
        const oAuth2Client = new OAuth2Client(
            process.env.CLIENT_ID,
            process.env.CLIENT_SECRET,
            redirectUrl
        );
        const result = await oAuth2Client.getToken(code);
        await oAuth2Client.setCredentials(result.tokens);
        console.log('Tokens acquired');
        const user = oAuth2Client.credentials;
        const data = await getUserData(user.access_token);
        const encodedData = encodeURIComponent(JSON.stringify(data));        
        res.redirect(`http://localhost:3000/login?data=${encodedData}`);
    } catch(err){
        next(err);
    }
})


module.exports = router;
