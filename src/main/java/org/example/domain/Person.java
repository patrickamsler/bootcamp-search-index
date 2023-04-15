package org.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Person {

  private final int id;
  private final String firstName;
  private final String lastName;
  private final int yearOfBirth;
  private final List<Address> addresses;

  @JsonCreator
  public Person(@JsonProperty("id") int id,
      @JsonProperty("firstName") String firstName,
      @JsonProperty("lastName") String lastName,
      @JsonProperty("yearOfBirth") int yearOfBirth,
      @JsonProperty("addresses") List<Address> addresses) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.yearOfBirth = yearOfBirth;
    this.addresses = addresses;
  }

  @JsonProperty("id")
  public int getId() {
    return id;
  }

  @JsonProperty("firstName")
  public String getFirstName() {
    return firstName;
  }

  @JsonProperty("lastName")
  public String getLastName() {
    return lastName;
  }

  @JsonProperty("yearOfBirth")
  public int getYearOfBirth() {
    return yearOfBirth;
  }

  @JsonProperty("addresses")
  public List<Address> getAddresses() {
    return addresses;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(firstName).append(" ").append(lastName).append(" (").append(yearOfBirth).append(")\n");
    for (Address address : addresses) {
      sb.append("- ").append(address).append("\n");
    }
    return sb.toString();
  }
}