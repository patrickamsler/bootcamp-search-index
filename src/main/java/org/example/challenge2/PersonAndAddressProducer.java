package org.example.challenge2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.challenge2.Message.MessageType;

public class PersonAndAddressProducer {

  private static final String TOPIC_NAME = "person-address-topic";
  private static final int NUM_PARTITIONS = 3;
  private static final short REPLICATION_FACTOR = 1;

  public static void main(String[] args) throws IOException {
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


    // Read the people and addresses from the JSON files
    ObjectMapper objectMapper = new ObjectMapper();
    File file = new File("people-only.json");
    List<Person> people = objectMapper.readValue(file, new TypeReference<>() {});

    // read the addresses from the JSON file
    File file2 = new File("addresses-only.json");
    List<Address> addresses = objectMapper.readValue(file2, new TypeReference<>() {});


    // Publish each person to the Kafka topic
    for (Person person : people) {
      Message message = new Message(person.getId(), MessageType.PERSON, person);
      String key = person.getId();
      int partition = Math.abs(key.hashCode()) % NUM_PARTITIONS;
      produceMessage(producer, partition, key, message);
    }

    // Publish each address to the Kafka topic
    for (Address address : addresses) {
      Message message = new Message(address.getId(), MessageType.ADDRESS, address);
      String key = address.getId();
      // use the personId as the key to ensure that the address and person are on the same partition
      int partition = Math.abs(address.getPersonId().hashCode()) % NUM_PARTITIONS;
      produceMessage(producer, partition, key, message);
    }
  }

  private static void produceMessage(Producer<String, String> producer, int partition, String key, Message value)
      throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String valueString = objectMapper.writeValueAsString(value);
    ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, partition, key, valueString);
    producer.send(record);
  }
}