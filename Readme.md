# Tech Bootcamp - Search Index

## Table of Contents
- [Overview](#overview)
- [Challenges](#challenges)
  - [Challenge 1 - Create the Indexer Service](#challenge-1---create-the-indexer-service)
  - [Challenge 2 - Consume messages from Multiple Services](#challenge-2---consume-messages-from-multiple-services)
  - [Challenge 3 - Improve the Indexer Service](#challenge-3---improve-the-indexer-service)
- [How to run](#how-to-run)
  - [Elastic Docker Setup](#elastic-docker-setup)
  - [Kafka Docker Setup](#kafka-docker-setup)
  - [Clean up](#clean-up)

## Overview

Welcome to the Quarkus-Kafka-Elasticsearch bootcamp challenge! 
This event is designed to test your skills in creating a scalable and efficient Elasticsearch indexer using Quarkus and Kafka. 
Your mission is to create a seamless integration between multiple backends and a centralized Elasticsearch datastore.

## Challenges

You will be provided with a Kafka topic and sample code to simulate multiple backends, each with their own database.
Your task is to develop the Indexer as a Quarkus application that consumes messages from the Kafka topic and indexes the data into a central readonly Elasticsearch index.
The solution should ensure data synchronization between Elasticsearch and the various backends, with a focus on searchability and performance 
avoiding the use of a relational database for full-text and fuzzy search.

Requirements:

* Indexer: Create a Quarkus application that consumes messages from the given Kafka topic and indexes the data into a single Elasticsearch index, considering only the searchable fields of the entities.
* Multi-field Search: Enable users to search multiple fields simultaneously across different backends. Returning just the entity IDs as the query result is sufficient. The actual data can be retrieved from the backend directly. The specific searchable fields are: lastname, firstname, city, and street.
* Data Synchronization: Ensure that Elasticsearch is always in-sync with the different backends, maintaining data integrity and consistency.
* Extensibility: Although partial updates and deletes are not considered in this challenge, design the indexer in a way that allows for easy extension to handle these operations in the future.

Deliverables:

* Source code for the Quarkus application that consumes messages from the Kafka topic and indexes them into Elasticsearch.
* Documentation detailing the architecture, data flow, and design decisions made during the development process.
* A brief demo showcasing the functionality and performance of the developed solution, including the multi-field search capability and data synchronization between Elasticsearch and backends.

### Challenge 1 - Create the indexer service
In this part you are tasked with creating the Indexer as a Quarkus application that indexes person data and their associated addresses from a single backend into a single Elasticsearch index.

![Architecture Diagram](doc/images/challenge1.png)

* Assume there is only one backend sending person data, including their associated addresses.
* If a person message comes in twice (e.g., because of an update), simply overwrite the existing document in Elasticsearch.
* Create a single Elasticsearch index to store the person and address data, choose a nested or parent/child document structure that is optimized for search performance.

A query for the created index might look like this:
```luce
{
  "_source": ["_id"],
  "query": {
    "multi_match": {
      "query": "John Smith Main St",
      "fields": ["firstName", "lastName", "addresses.street"]
    }
  }
}
```

### Challenge 2 - Consume messages from multiple services
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