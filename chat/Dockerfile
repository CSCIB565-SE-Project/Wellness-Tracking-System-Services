FROM node:20.11.0-alpine

WORKDIR /usr/src/app
COPY ./chat/package*.json ./
RUN npm ci --omit=dev
COPY ./chat/src ./src

ENV STREAM_APP_ID="1294184"
ENV STREAM_API_KEY="v5zqy2qw283c"
ENV STREAM_API_SECRET="txhsnr8dh8h4ztm243zhhu27kaxqeha7ya8q25x4gvm7xvpjp3y8k9q27b653sd4"
ENV JWT_SECRET="357638792F423F4428472B4B6250655368566D597133743677397A2443264629"
ENV DB_HOST="wellness-tracking-rdb.mysql.database.azure.com"
ENV DB_USER="serviceuser"
ENV DB_PASS="136Mm136"
ENV DB_NAME="fitinc"

CMD npm start