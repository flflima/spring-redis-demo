# Spring Redis Demo

Starting redis:

```shell
docker-compose up
```

Executing:

```shell
curl -X POST -H 'Content-Type: application/json' \
-d '{"text": "Hello World!"}'
```

Installing and running Artillery:

```shell
npm install -g artillery

artillery run artillery.yml
```