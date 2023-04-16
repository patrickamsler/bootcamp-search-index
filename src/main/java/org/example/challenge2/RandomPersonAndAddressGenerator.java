package org.example.challenge2;

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
import java.util.UUID;

public class RandomPersonAndAddressGenerator {

  private static final String[] FIRST_NAMES = {"John", "Jane", "Michael", "Emily", "David", "Samantha"};
  private static final String[] LAST_NAMES = {"Smith", "Johnson", "Lee", "Garcia", "Davis", "Taylor"};
  private static final String[] CITIES = {"New York,NY", "Los Angeles,CA", "Chicago,IL", "Houston,TX", "Miami,FL", "Seattle,WA"};
  private static final String[] STREET_NAMES = {"Main St", "First St", "Second St", "Third St", "Fourth St",
      "Fifth Ave", "Sixth Ave"};


  private static final Random RANDOM = new Random();

  public static void main(String[] args) throws IOException {
    List<Person> people = generateRandomPeople(10);
    List<Address> addresses = generateRandomAddress(people);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

    String peopleJson = objectMapper.writeValueAsString(people);
    String addressesJson = objectMapper.writeValueAsString(addresses);

    // Write people JSON to console and file
    System.out.println("People JSON:");
    System.out.println(peopleJson);
    Path peopleFilePath = Paths.get("people-only.json");
    Files.write(peopleFilePath, Collections.singleton(peopleJson), StandardCharsets.UTF_8, StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING);

    // Write addresses JSON to console and file
    System.out.println("Addresses JSON:");
    System.out.println(addressesJson);
    Path addressesFilePath = Paths.get("addresses-only.json");
    Files.write(addressesFilePath, Collections.singleton(addressesJson), StandardCharsets.UTF_8, StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING);
  }

  public static List<Person> generateRandomPeople(int numPeople) {
    List<Person> people = new ArrayList<>();
    for (int i = 0; i < numPeople; i++) {
      String personId = UUID.randomUUID().toString();
      String firstName = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
      String lastName = LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
      int yearOfBirth = RANDOM.nextInt(50) + 1970;

      Person person = new Person(personId, firstName, lastName, yearOfBirth);
      people.add(person);
    }
    return people;
  }

  public static List<Address> generateRandomAddress(List<Person> people) {
    List<Address> addresses = new ArrayList<>();
    for (Person person : people) {
      // Some people have multiple addresses
      int numAddresses = RANDOM.nextInt(2) + 1;
      for (int i = 0; i < numAddresses; i++) {
        String addressId = UUID.randomUUID().toString();
        String[] cityAndState = CITIES[RANDOM.nextInt(CITIES.length)].split(",");
        String city = cityAndState[0];
        String state = cityAndState[1];
        String street = STREET_NAMES[RANDOM.nextInt(STREET_NAMES.length)] + " " + (RANDOM.nextInt(1000) + 1);

        Address address = new Address(addressId, person.getId(), street, city, state);
        addresses.add(address);
      }
    }
    return addresses;
  }

}
