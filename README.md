api-gateway
===========

An API gateway layer using the spring-cloud Zuul proxy

### Build
```
./gradlew build
```

### Run only api-gateway
~~./gradlew :bootRun~~
wont work due to this bug -- https://github.com/spring-cloud/spring-cloud-netflix/issues/60

Instead do it the old fashion way...

```
java -jar api-gateway.jar
```

### Run All the applications
See [spring-cloud-examples](https://github.com/wgorder/spring-cloud-examples)
