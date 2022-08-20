package com.demo.kafkaBinder.service.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Message {
  private String name;
  private String phone;

  @Override
  public String toString() {
    return "Message{" +
             name + '\'' +
            phone + '\'' +
            '}';
  }
}
