package org.example.challenge1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomPersonGenerator {

  private static final String[] FIRST_NAMES = {"John", "Jane", "Michael", "Emily", "David", "Samantha"};
  private static final String[] LAST_NAMES = {"Smith", "Johnson", "Lee", "Garcia", "Davis", "Taylor"};
  private static final String[] CITIES = {"New York", "Los Angeles", "Chicago", "Houston", "Miami", "Seattle"};
  private static final String[] STATES = {"NY", "CA", "IL", "TX", "FL", "WA"};
  private static final String[] STREET_NAMES = {"Main St", "First St", "Second St", "Third St", "Fourth St",
      "Fifth Ave", "Sixth Ave"};


  private static final Random RANDOM = new Random();

  private static int personIdCounter = 1;
  private static int addressIdCounter = 1;

  public static void main(String[] args) throws IOException {
    List<Person> people = generateRandomPeople(10);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

    String json = objectMapper.writeValueAsString(people);

    // Write JSON to console
    System.out.println(json);

    // Write JSON to file
    Path filePath = Paths.get("people-with-address.json");
    Files.write(filePath, Collections.singleton(json), StandardCharsets.UTF_8, StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING);
  }

  public static List<Person> generateRandomPeople(int numPeople) {
    List<Person> people = new ArrayList<>();
    for (int i = 0; i < numPeople; i++) {
      int personId = personIdCounter++;
      String firstName = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
      String lastName = LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
      int yearOfBirth = RANDOM.nextInt(50) + 1970;

      List<Address> addresses = new ArrayList<>();
      Address address1 = generateRandomAddress();
      addresses.add(address1);

      // a person may have a second address with 50% probability
      if (RANDOM.nextBoolean()) {
        Address address2 = generateRandomAddress();
        addresses.add(address2);
      }

      Person person = new Person(personId, firstName, lastName, yearOfBirth, addresses);
      people.add(person);
    }
    return people;
  }

  public static Address generateRandomAddress() {
    int addressId = addressIdCounter++;
    String street = STREET_NAMES[RANDOM.nextInt(CITIES.length)] + " " + (RANDOM.nextInt(1000) + 1);
    String city = CITIES[RANDOM.nextInt(CITIES.length)];
    String state = STATES[RANDOM.nextInt(STATES.length)];
    return new Address(addressId, street, city, state);
  }

}
