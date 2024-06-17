package com.rosendo.app_chat.domain.models;

import com.rosendo.app_chat.domain.enums.MessageType;

import java.util.Objects;

public class ChatMessageModel {

  private String content;
  private String sender;
  private MessageType type;
  
  public ChatMessageModel() {}

  public ChatMessageModel(String content, String sender, MessageType type) {
    this.content = content;
    this.sender = sender;
    this.type = type;
  }

  private ChatMessageModel(Builder builder) {
    this.content = builder.content;
    this.sender = builder.sender;
    this.type = builder.type;
  }

  public static class Builder {
    private String content;
    private String sender;
    private MessageType type;

    public Builder content(String content) {
      this.content = content;
      return this;
    }

    public Builder sender(String sender) {
      this.sender = sender;
      return this;
    }

    public Builder type(MessageType type) {
      this.type = type;
      return this;
    }

    public ChatMessageModel build() {
      return new ChatMessageModel(this);
    }
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public MessageType getType() {
    return type;
  }

  public void setType(MessageType type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ChatMessageModel that = (ChatMessageModel) o;
    return Objects.equals(content, that.content) && Objects.equals(sender, that.sender) && type == that.type;
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(content);
    result = 31 * result + Objects.hashCode(sender);
    result = 31 * result + Objects.hashCode(type);
    return result;
  }
}
