const { BlobServiceClient } = require("@azure/storage-blob");
const Video = require("../models/Video.js");
require('dotenv').config();

const deleteVideoFromBlob = async (videoUrl) => {
    const connStr = process.env.AZ_SA_CONN_STR;
    
    try {
        const blobServiceClient = BlobServiceClient.fromConnectionString(connStr);
        const containerClient = blobServiceClient.getContainerClient('wellness-tracking-container');
        const blobClient = containerClient.getBlobClient(videoUrl);
        await blobClient.delete();
        return true;
    } catch (error) {
        console.error("Error deleting video:", error);
        return false;
    }
};

module.exports = { deleteVideoFromBlob };
