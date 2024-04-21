const mysql = require('mysql');
require('dotenv').config();
const fs = require('fs');
const path = require('path');

const pool = mysql.createPool({
  connectionLimit : 10,
  host            : process.env.DB_HOST, 
  user            : process.env.DB_USER, 
  password        : process.env.DB_PASS, 
  database        : process.env.DB_NAME, 
  port            : 3306,
  ssl: {
    ca: fs.readFileSync(path.join(__dirname, 'DigiCertGlobalRootG2.crt.pem'))
  }
});


module.exports = pool;
