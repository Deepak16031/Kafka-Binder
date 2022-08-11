package com.demo.kafkaBinder.service.kafka;

import com.demo.kafkaBinder.service.Model.Message;
import kafka.Kafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.stereotype.Component;

@Component
public class DemoSupplier {

  @Autowired
  @Qualifier("embeddedKafkaBroker")
  public EmbeddedKafkaBroker kafkaBroker;

  @Autowired
  private KafkaTemplate<String,String> kafkaTemplate;

  @Value("${demo.topic}")
  private String topicName;
//
//  @Autowired
//  private KafkaTemplate<String, Message> messageKafkaTemplate;

  public void supply(){
    for(int i =0 ;i<100;i++){
      kafkaTemplate.send(topicName, "Message:"+i*2);
    }
  }

//  public void supplyMessages(){
//    for(int i =0;i<100;i++){
//      messageKafkaTemplate.send(topicName,
//          Message.builder()
//              .name("name:"+i*2)
//              .phone("phone:9"+i*73)
//              .build());
//    }
//  }



}
