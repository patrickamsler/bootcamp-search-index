package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import org.example.domain.Person;

public class PersonProducer {

  private static final String TOPIC_NAME = "person-full-topic";
  private static final int NUM_PARTITIONS = 3;
  private static final short REPLICATION_FACTOR = 1;

  public static void main(String[] args) throws IOException {
    // create the topic if not exists
    Properties adminProps = new Properties();
    adminProps.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    AdminClient adminClient = AdminClient.create(adminProps);
    NewTopic newTopic = new NewTopic(TOPIC_NAME, NUM_PARTITIONS, REPLICATION_FACTOR);
    adminClient.createTopics(Collections.singleton(newTopic));
    adminClient.close();

    Properties producerProps = new Properties();
    producerProps.put("bootstrap.servers", "localhost:9092");
    producerProps.put("acks", "all");
    producerProps.put("retries", 0);
    producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    Producer<String, String> producer = new KafkaProducer<>(producerProps);

    ObjectMapper objectMapper = new ObjectMapper();
    File file = new File("people-with-address.json");
    List<Person> people = objectMapper.readValue(file, new TypeReference<>() {});

    // Publish each person to the Kafka topic
    for (Person person : people) {
      String json = objectMapper.writeValueAsString(person);
      String key = Integer.toString(person.getId());
      int partition = Math.abs(key.hashCode()) % NUM_PARTITIONS;

      ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, partition, key, json);
      producer.send(record);
    }

    producer.close();
  }
}