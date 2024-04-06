const jwt = require('jsonwebtoken');
const { connect } = require('getstream');
const axios = require('axios');
const pool = require('.././db'); 
const { promisify } = require('util');
const StreamChat = require('stream-chat').StreamChat;
require('dotenv').config();
pool.query = promisify(pool.query);


const api_key = process.env.STREAM_API_KEY;
const api_secret = process.env.STREAM_API_SECRET;
const app_id = process.env.STREAM_APP_ID;
const jwt_secret = Buffer.from(process.env.JWT_SECRET, 'base64');

const verifyAndGenerateToken = async (req, res) => {
    try {
        // const { token: jwtToken } = req.body;

        // // Verify the JWT token sent by the client
        // const decoded = jwt.verify(jwtToken, jwt_secret);
        // const username = decoded.sub; // Using username from the token

        // console.log("Decoded JWT, username:", username);
        // Query the database for the userId using the username

        //ddrake for local test 

        const username = 'ddrakee';
        const serverClient = StreamChat.getInstance(api_key, api_secret, app_id);
        
        const results = await pool.query('SELECT id, role FROM fitinc.user WHERE username = ?', [username]);

        if (results.length === 0) {
            return res.status(404).json({ message: 'User not found' });
        }

        const userId = results[0].id.toString();
        const userRole = results[0].role.toLowerCase();

        const streamToken = serverClient.createToken(userId);
        console.log("User ID used in token generation:", userId);
        console.log("Role used in Updating the user", userRole);

        await serverClient.upsertUser({
            id: userId,
            role: 'professional'
            });

        console.log("Stream Chat token:", streamToken);

        return res.status(200).json({ streamToken, userId });

    } catch (error) {
        console.error('Error:', error);
        return res.status(500).json({ message: 'Internal server error' });
    }
        
};

// ////////////////////////////////////////////////////////////////////////////////////////


// // Ensure these are correctly set in your .env file or your environment variables
// const API_KEY = process.env.STREAM_API_KEY;
// const API_SECRET = process.env.STREAM_API_SECRET;
// const APP_REGION = 'ohio'; // Adjust according to your Stream app region
// const USER_ID = '14'; // Example user ID

// // Function to generate a server-side JWT token for authentication
// function generateServerSideToken(userId, apiSecret) {
//     return jwt.sign({ user_id: userId }, apiSecret, { expiresIn: '60 minutes', algorithm: 'HS256' });
// }

// // Function to update a user's role with server-side authentication
// async function updateUserRole(userId, newData) {
//     const token = generateServerSideToken(userId, API_SECRET); // Generate the JWT token
//     const url = `https://${APP_REGION}-chat.stream-io-api.com/api/user/${userId}/?api_key=${API_KEY}`; // Include the API key as a query parameter

//     try {
//         const response = await axios.get(url, { data: newData }, {
//             headers: {
//                 'Authorization': token, // Use the generated JWT token for Authorization
//                 'Stream-Auth-Type': 'jwt',
//                 'Content-Type': 'application/json',
//             }
//         });
//         console.log('User updated successfully:', response.data);
//     } catch (error) {
//         console.error('Error updating user:', error.response ? error.response.data : error.message);
//     }
// }

// // Example data to update, assuming role can be updated this way
// const newData = {
//     role: 'admin', // This assumes you can directly update the role, which might not be accurate
//     // other data fields you want to update
//     modified: true,
// };

// updateUserRole(USER_ID, newData);




module.exports = { verifyAndGenerateToken }