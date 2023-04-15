# Tech Bootcamp - Search Index

- [How to run](#how-to-run)
    - [Elastic Docker Setup](#elastic-docker-setup)
    - [Kafka Docker Setup](#kafka-docker-setup)
    - [Clean up](#clean-up)


## How to run
Please note that the instructions provided are specific to running Elastic and Kafka using Docker. If you have Elastic and Kafka running on your local machine, you can skip this step.

### Elastic Docker Setup
1. `cd bootcamp-search-index/docker/elastic`
2. `docker-compose up -d`

Access Kibana: http://localhost:5601

username: `elastic`

password: `elastic`

### Kafka Docker Setup
1. `cd elastic-playground/docker/kafka`
2. `docker-compose up -d`

Access Confluent Kafka UI: http://localhost:9021

###  Clean up
`docker-compose down -v`

Removes all containers, volumes and networks