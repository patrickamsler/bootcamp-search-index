

## How to run Elastic
1. `cd bootcamp-search-index/docker/elastic`
2. `docker-compose up -d`

Access Kibana: http://localhost:5601

username: `elastic`

password: `elastic`

## How to run Kafka
1. `cd elastic-playground/docker/kafka`
2. `docker-compose up -d`

Access Confluent Kafka UI: http://localhost:9021

##  Clean up
`docker-compose down -v`

Removes all containers, volumes and networks