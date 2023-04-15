package org.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

  private final int id;
  private final String street;
  private final String city;
  private final String state;

  @JsonCreator
  public Address(
      @JsonProperty("id") int id,
      @JsonProperty("street") String street,
      @JsonProperty("city") String city,
      @JsonProperty("state") String state) {
    this.id = id;
    this.street = street;
    this.city = city;
    this.state = state;
  }

  public int getId() {
    return id;
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