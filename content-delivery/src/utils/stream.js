require('dotenv').config();
const AzureStorageBlob = require("@azure/storage-blob");
const { BlobServiceClient } = require("@azure/storage-blob");
const Video = require("../models/Video.js"); 

const streamVideo = async (req, res, next) => {
    videoUrl = req.query.videoUrl;
    const connStr = process.env.AZ_SA_CONN_STR;
    const blobServiceClient = BlobServiceClient.fromConnectionString(connStr);
    const containerClient = blobServiceClient.getContainerClient('wellness-tracking-container');
    const blobClient = containerClient.getBlobClient(videoUrl);

    const range = req.headers.range;
    if (!range) {
        return res.status(400).send("Requires Range header");
    }

    try {
        const properties = await blobClient.getProperties();
        const videoSize = properties.contentLength;

        const CHUNK_SIZE = 10 ** 6; // 1MB
        const start = Number(range.replace(/\D/g, ""));
        const end = Math.min(start + CHUNK_SIZE, videoSize - 1);

        const contentLength = end - start + 1;
        const headers = {
        "Content-Range": `bytes ${start}-${end}/${videoSize}`,
        "Accept-Ranges": "bytes",
        "Content-Length": contentLength,
        "Content-Type": "video/mp4",
        };

        res.writeHead(206, headers);
        const downloadResponse = await blobClient.download(start, end);
        downloadResponse.readableStreamBody.pipe(res);
    } catch (error) {
        console.error("Error streaming video:", error);
        res.status(500).send("Internal Server Error");
    }
};

module.exports = { streamVideo };