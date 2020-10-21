# xray-agent-segment-issue

To start, run `./gradlew bootRun`

The repro expects an xray-daemon to be running at localhost:2000
Setup one by running 
`docker pull amazon/aws-xray-daemon`
`docker run -d --name xray-daemon -v $HOME/.aws/credentials:/root/.aws/credentials:ro -p 2000:2000 -p 2000:2000/udp amazon/aws-xray-daemon -o -n eu-west-1`

The repro service exposes two endpoints
http://localhost:5000/xray/withIssue
and
http://localhost:5000/xray/withoutIssue
and will launch with Xray debug loggin enabled.

Hitting `/xray/withoutIssue` will give log output explaing the issue reported in https://github.com/aws/aws-xray-sdk-java/issues/227
