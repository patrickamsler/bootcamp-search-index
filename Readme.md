# Tech Bootcamp - Search Index

## Table of Contents
- [Overview](#overview)
- [Challenges](#challenges)
  - [Challenge 1 - Create the Indexer Service](#challenge-1---create-the-indexer-service)
  - [Challenge 2 - Consume the messages from Multiple Services](#challenge-2---consume-the-messages-from-multiple-services)
  - [Challenge 3 - Improve the Indexer Service](#challenge-3---improve-the-indexer-service)
- [How to run](#how-to-run)
  - [Elastic Docker Setup](#elastic-docker-setup)
  - [Kafka Docker Setup](#kafka-docker-setup)
  - [Clean up](#clean-up)

## Overview

TODO add description

## Challenges

### Challenge 1 - Create the Indexer Service

![Architecture Diagram](doc/images/challenge1.png)

### Challenge 2 - Consume the messages from Multiple Services
![Architecture Diagram](doc/images/challenge2.png)

### Challenge 3 - Improve the Indexer Service

## How to run
Please note that the instructions provided are specific to running Elastic and Kafka using Docker. If you have Elastic and Kafka running on your local machine, you can skip this step.

### Run Elastic with Docker
There is a Docker Compose file that defines two services for running Elastic and Kibana on your local machine.

1. `cd bootcamp-search-index/docker/elastic`
2. `docker-compose up -d`


Access Kibana UI: http://localhost:5601

username: `elastic`

password: `elastic`

### Run Kafka with Docker
The Docker Compose file sets up a Confluent Kafka cluster with a Zookeeper node, a single broker, a Schema Registry, Kafka Connect, the Control Center and a KSQLDB server.

1. `cd bootcamp-search-index/docker/kafka`
2. `docker-compose up -d`

Access Kafka Control Center UI: http://localhost:9021

###  Clean up
`docker-compose down -v`

Removes all containers, volumes and networks