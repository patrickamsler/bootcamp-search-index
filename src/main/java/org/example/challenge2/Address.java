package org.example.challenge2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

  private final String id;
  private final String personId;
  private final String street;
  private final String city;
  private final String state;

  @JsonCreator
  public Address(
      @JsonProperty("id") String id,
      @JsonProperty("personId") String personId,
      @JsonProperty("street") String street,
      @JsonProperty("city") String city,
      @JsonProperty("state") String state) {
    this.id = id;
    this.personId = personId;
    this.street = street;
    this.city = city;
    this.state = state;
  }

  public String getId() {
    return id;
  }

  public String getPersonId() {
    return personId;
  }

  public String getStreet() {
    return street;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  @Override
  public String toString() {
    return street + ", " + city + ", " + state;
  }
}