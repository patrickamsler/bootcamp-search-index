package org.example.challenge2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Person {

  private final String id;
  private final String firstName;
  private final String lastName;
  private final int yearOfBirth;

  @JsonCreator
  public Person(@JsonProperty("id") String id,
      @JsonProperty("firstName") String firstName,
      @JsonProperty("lastName") String lastName,
      @JsonProperty("yearOfBirth") int yearOfBirth) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.yearOfBirth = yearOfBirth;
  }

  @JsonProperty("id")
  public String getId() {
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

  @Override
  public String toString() {
    return firstName + " " + lastName + " (" + yearOfBirth + ")\n";
  }
}