# vKestrel



The kestrel brings good things every morning. <br/>
It is a blog post in Korea <br/>

---
## Environment

[![version](https://img.shields.io/badge/springboot-2.7.9-00bfb3?style=flat&logo=spring-boot)]()
[![version](https://img.shields.io/badge/AOP--00bfb3?style=flat&logo=spring-boot)]()
[![version](https://img.shields.io/badge/JPA--00bfb3?style=flat&logo=spring)]()
[![version](https://img.shields.io/badge/p6spy-1.5.7-00bfb3?style=flat&logo=)]()
[![version](https://img.shields.io/badge/validation-2.5.2-00bfb3?style=flat&logo=)]()
[![version](https://img.shields.io/badge/spring_batch--00bfb3?style=flat&logo=spring)]()
[![version](https://img.shields.io/badge/gitHubactions--00bfb3?style=flat&logo=git)]()
[![version](https://img.shields.io/badge/springboot_actuator--00bfb3?style=flat&logo=spring-boot)]()
[![version](https://img.shields.io/badge/prometheus--00bfb3?style=flat&logo=prometheus)]()
[![version](https://img.shields.io/badge/prometheus_mysqld_exporter--00bfb3?style=flat&logo=prometheus)]()
[![version](https://img.shields.io/badge/grafana--00bfb3?style=flat&logo=grafana)]()
[![version](https://img.shields.io/badge/flyway-6.4.2-00bfb3?style=flat&logo=flyway)]()
[![version](https://img.shields.io/badge/slack_bot-1.27.3-00bfb3?style=flat&logo=slack)]()
[![version](https://img.shields.io/badge/rome-1.10.0-00bfb3?style=flat&logo=)]()
[![version](https://img.shields.io/badge/jsoup-1.14.2-00bfb3?style=flat&logo=)]()
[![version](https://img.shields.io/badge/springdoc-1.6.9-00bfb3?style=flat&logo=swagger)]()
[![version](https://img.shields.io/badge/loki-2.9.1-00bfb3?style=flat&logo=loki)]()
[![version](https://img.shields.io/badge/promtail-2.9.1-00bfb3?style=flat&logo=promtail)]()

## Host setup


- [Docker Engine][docker-install] version **18.06.0** or newer
- [Docker Compose][compose-install] version **1.26.0** or newer (including [Compose V2][compose-v2])
- 2.0 GB of RAM


By default, the stack exposes the following ports

- 8791 : application
- 3000 : grafana
- 9094 : prometheus
- 3100 : loki
- 9104 : db-exporter


```

  +--------+        +----------+        +------+           
  | server | <----- | promtail | -----> | loki |  ------------------|   
  +--------+        +----------+        +------+                    |
     |                                                              >
  +------+        +-------------+        +------------+         +-----------+  
  |  DB  | <----- | DB Exporter | -----> | Prometheus |  --->   |  Grafana  |
  +------+        +-------------+        +------------+         +-----------+
     |                                        >
  +----------+                                |
  | actuator | -------------------------------|
  +----------+      
```

## batch job
- Operate at 9 a.m. weekday day
- It performs three tasks.
  1) Synchronize the library(resources/*.library.json)
  2) Bringing posts from the library
  3) Announces newly added posts.



# Usage
## RUN
### clone this repository
```shell
git clone https://github.com/birariro/vKestrel.git
```

### Docker Compose setup and start
You will need to install Docker and docker-compose.

a Docker Compose setup is provided. It comes with the following :

- mariadb:10.8.3
``` shell
 $ docker-compose up -d
```
Operation Docker Compose setup is provided. It comes with the following :
``` shell
 $ docker compose -f docker-compose.operation.yml up --build -d
```
- prometheus
- grafana
- loki
- promtail
- DB Exporter





### Registration Slack WebHook
register webHook to receive a message from application
Register through API
``` json
POST /member/webhook
{
  "url" : "[your webhook url]",
}
```


## ETC
### Commit convention hook 
Provision of commit-msg.sh for git commit convention

``` shell 
 $ cp tools/hooks/commit-msg.sh .git/hooks/commit-msg 
```

## application path
``` 
actuator : http://localhost:8791/actuator  
grafana : http://localhost:3000 
prometheus : http://localhost:9094
```

## grafana data sources path
```
prometheus : http://vk-prometheus:9090
loki : http://vk-loki:3100
```