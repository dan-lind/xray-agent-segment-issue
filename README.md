# xray-agent-segment-issue

To start, run `./gradlew bootRun`

The repro expects an xray-daemon to be running at localhost:2000
Setup one by running
```
docker pull amazon/aws-xray-daemon
docker run -d --name xray-daemon -v $HOME/.aws/credentials:/root/.aws/credentials:ro -p 2000:2000 -p 2000:2000/udp amazon/aws-xray-daemon -o -n eu-west-1
```

The repro service exposes two endpoints
http://localhost:5000/xray/withIssue
and
http://localhost:5000/xray/withoutIssue
and will launch with Xray debug loggin enabled.

Hitting `/xray/withoutIssue` will give the expected result, as it starts a CompletableFuture and then blocks until it is done.
Hitting `/xray/withIssue` will start a CompletableFuture, but the main thread will _not_ wait for it to finish.
This also gives log output explaing the issue reported in https://github.com/aws/aws-xray-sdk-java/issues/227
