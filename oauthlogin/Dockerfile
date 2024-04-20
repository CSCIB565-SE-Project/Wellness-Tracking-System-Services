FROM node:20.11.0-alpine

WORKDIR /usr/src/app
COPY package*.json ./
RUN npm ci --omit=dev
COPY ./src ./src

ENV CLIENT_ID="91970816576-0qu2hpchnq9ejp1hgu65dq2pnoigjio9.apps.googleusercontent.com"
ENV CLIENT_SECRET="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"

CMD npm start