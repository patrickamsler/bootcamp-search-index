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

The Docker Compose file defines two services for running Elastic and Kibana on your local machine.

Access Kibana UI: http://localhost:5601

username: `elastic`

password: `elastic`

### Kafka Docker Setup
1. `cd bootcamp-search-index/docker/kafka`
2. `docker-compose up -d`

The Docker Compose file sets up a Confluent Kafka cluster with a Zookeeper node, a single broker, a Schema Registry, Kafka Connect node, the Control Center and a KSQLDB server.

Access Kafka Control Center UI: http://localhost:9021

###  Clean up
`docker-compose down -v`

Removes all containers, volumes and networks