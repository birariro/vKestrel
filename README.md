# Visit Knowledge

Knowledge comes to you every morning.

---

## Docker Compose setup
You will need to install Docker and docker-compose.

a Docker Compose setup is provided. It comes with the following databases:

- mariadb:10.8.3
- redis:7.0.4

```
 $ docker-compose up -d
```


## Environment
- JPA
- AOP
- p6spy (v1.5.7)
- validation (v2.5.2)
- SlackBot (v1.27.3)
- spring-batch
- GitHub Actions

## batch job
- Operate at 10 a.m. every day
- It performs three tasks.
  1) Synchronize the library(resources/*.library.json) 
  2) Bringing posts from the library
  3) Announces newly added posts.


## Hooks
Provision of commit-msg.sh for git commit convention

Active
```
cp hooks/commit-msg.sh .git/hooks/commit-msg
```


## Usage


### Registration
Registration SlackBot
```
[Slack]
POST /knowledge/reg
{
  "token" : "[your bot token]",
  "channel" : "[your bot Channel id]"
}
```

option error alert SlackBot
```
[Slack]
POST /error/reg
{
  "token" : "[your bot token]",
  "channel" : "[your bot Channel id]"
}
```

### redis command
```
> redis-cli 
    > keys * 
    > get [key]
    > flushall 
```