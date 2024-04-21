const jwt = require('jsonwebtoken');
const { connect } = require('getstream');
const axios = require('axios');
const pool = require('../db'); 
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
        const { token: jwtToken } = req.body;

        // // Verify the JWT token sent by the client
        const decoded = jwt.verify(jwtToken, jwt_secret);
        const id = decoded.sub; // Using id from the token

        console.log("Decoded JWT, id:", id);
        // Query the database for the role using the id

        const serverClient = StreamChat.getInstance(api_key, api_secret, app_id);
        
        const results = await pool.query('SELECT role FROM fitinc.user WHERE id = ?', [id]);

        if (results.length === 0) {
            return res.status(404).json({ message: 'User not found' });
        }

        
        const userRole = results[0].role.toLowerCase();

        const streamToken = serverClient.createToken(id);
        console.log("User ID used in token generation:", id);
        console.log("Role used in Updating the user", userRole);

        await serverClient.upsertUser({
            id: id,
            role: userRole,
            });
        
        // If the user is not an admin, add them to a support channel and add all admins to that channel
        if (userRole !== 'admin') {
            // Query for all admin IDs
            const adminResults = await pool.query('SELECT id FROM fitinc.user WHERE role = ?', ['admin']);
            const adminIds = adminResults.map(admin => admin.id.toString());

            console.log("these are the IDS of the admins that are being added to the chat.",adminIds);

            const channelId = `support_${id}`;
            // Create or update the support channel, add the user and all admins
            const channel = serverClient.channel('team', channelId, {
                created_by_id: id,
                name: `Support for User ${id}`,
                members: [id, ...adminIds],
            });
            await channel.create();
            console.log("channel created");
        }
        console.log("Stream Chat token:", streamToken);

        return res.status(200).json({ streamToken, id });

    } catch (error) {
        console.error('Error:', error);
        return res.status(500).json({ message: 'Internal server error' });
    }
        
};

module.exports = { verifyAndGenerateToken }