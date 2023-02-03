# PlayDay


### Docker Compose setup
You will need to install Docker and docker-compose.

a Docker Compose setup is provided. It comes with the following databases:

- mariadb:10.8.3

```
 $ docker-compose up -d
```


### Environment
- JPA
- AOP
- p6spy (v1.5.7)


### Hooks
Provision of commit-msg.sh for git commit convention

Active
```
cp hooks/commit-msg.sh .git/hooks/commit-msg
```
