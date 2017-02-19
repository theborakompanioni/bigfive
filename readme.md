big five
========
- gradle
- spring boot/jpa
- sqlite
- jetty


## Development

### Spring Boot
```
$ ./gradlew bootRun
```
### Build & Run
```
$ ./gradlew build && java -jar build/libs/tbk-bigfive-<version>.jar
```

```
$ curl localhost:8080/health
{"status":"UP","diskSpace":{"status":"UP","total":397635555328,"free":328389529600,"threshold":10485760}}}
```

### Docker
```
$ docker build -t tbk/tbk-bigfive .
$ docker run -t -i -p 8080:8080 tbk/tbk-bigfive
```