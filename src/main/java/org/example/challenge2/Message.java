package org.example.challenge2;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class Message {

  public enum MessageType {
    @JsonProperty("PERSON")
    PERSON,

    @JsonProperty("ADDRESS")
    ADDRESS;

    @JsonValue
    public String toValue() {
      return name();
    }

    @JsonCreator
    public static MessageType fromValue(String value) {
      return valueOf(value);
    }
  }

  private final String id;

  @JsonProperty("messageType")
  private final MessageType messageType;

  private final Object payload;

  @JsonCreator
  public Message(
      @JsonProperty("id") String id,
      @JsonProperty("messageType") MessageType messageType,
      @JsonProperty("payload") Object payload) {
    this.id = id;
    this.messageType = messageType;
    this.payload = payload;
  }

  public String getId() {
    return id;
  }

  public MessageType getMessageType() {
    return messageType;
  }

  public Object getPayload() {
    return payload;
  }
}
