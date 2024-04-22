FROM node:20.11.0-alpine

WORKDIR /usr/src/app
COPY ./content-delivery/package*.json ./
RUN npm ci --omit=dev
COPY ./content-delivery/src ./src

ENV GCS_BUCKET="wellness-tracking-system-content-bucket"
ENV AZ_SA_CONN_STR="BlobEndpoint=https://wellnesstrackingsa.blob.core.windows.net/;QueueEndpoint=https://wellnesstrackingsa.queue.core.windows.net/;FileEndpoint=https://wellnesstrackingsa.file.core.windows.net/;TableEndpoint=https://wellnesstrackingsa.table.core.windows.net/;SharedAccessSignature=sv=2022-11-02&ss=bfqt&srt=sco&sp=rwdlacupiytfx&se=2024-06-01T03:07:21Z&st=2024-03-26T19:07:21Z&spr=https,http&sig=wrxJCD5%2FjyCpm%2BtZ3dJcl%2Bk%2FumwIkwNtV9SzbCO4%2B7A%3D"
ENV MONGODB="mongodb+srv://contentuser:Wellness123@wellnesstrackingcontent.851h7v8.mongodb.net/wellnesstrackingcontent?retryWrites=true&w=majority&appName=wellnesstrackingcontentdb"

CMD npm start