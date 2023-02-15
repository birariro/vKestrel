# Visit Knowledge

Knowledge comes to you every morning.

---

### Docker Compose setup
You will need to install Docker and docker-compose.

a Docker Compose setup is provided. It comes with the following databases:

- mariadb:10.8.3
- redis:7.0.4

```
 $ docker-compose up -d
```


### Environment
- JPA
- AOP
- p6spy (v1.5.7)
- validation (v2.5.2)
- SlackBot (v1.27.3)
- spring-batch


### Hooks
Provision of commit-msg.sh for git commit convention

Active
```
cp hooks/commit-msg.sh .git/hooks/commit-msg
```


###Usage
create application-key.yml
```
mail:
  host: [smtp host]
  port: [smtp port]
  username: [smtp id]
  password: [smtp pwd]

```

Registration Email or SlackBot
```
[Email]
POST /email/reg
{
    "email" : "[your email]"
}

[Slack]
POST /slack/reg
{
  "normalBotToken" : "[your main bot token]",
  "normalBotChannel" : "[your main bot Channel id]",
  "errorBotToken" : "[your main bot token]",
  "errorBotChannel" :  "[your main bot Channel id]"
}
```

### redis
```
> redis-cli 
    > keys * 
    > get [key]
    > flushall 
```