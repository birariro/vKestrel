# Visit Knowledge

Knowledge comes to you every morning.

---
## Environment
- JPA
- AOP
- p6spy (v1.5.7)
- validation (v2.5.2)
- SlackBot (v1.27.3)
- spring-batch
- GitHub Actions
- Spring Boot Actuator
- prometheus
- grafana
- thymeleaf
- bootstrap (v5.1.3)
- flyway (v6.4.2)

## batch job
- Operate at 9 a.m. every day
- It performs three tasks.
  1) Synchronize the library(resources/*.library.json)
  2) Bringing posts from the library
  3) Announces newly added posts.



# Usage
## Docker Compose setup
You will need to install Docker and docker-compose.

a Docker Compose setup is provided. It comes with the following :

- mariadb:10.8.3
- prometheus
- grafana

``` shell
 $ docker-compose up -d
```

## Hooks
Provision of commit-msg.sh for git commit convention

``` shell 
 $ cp hooks/commit-msg.sh .git/hooks/commit-msg 
```

## application path
``` 
actuator : http://localhost:8791/actuator  
prometheus : http://localhost:9094
grafana : http://localhost:3000 
```

## API
### Registration
Registration SlackBot
``` json
POST /knowledge/reg
{
  "token" : "[your bot token]",
  "channel" : "[your bot Channel id]"
}
```

option error alert SlackBot
``` json
POST /error/reg
{
  "token" : "[your bot token]",
  "channel" : "[your bot Channel id]"
}
```