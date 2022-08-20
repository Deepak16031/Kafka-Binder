package com.demo.kafkaBinder.service.kafka;

import com.demo.kafkaBinder.service.Model.Message;
import kafka.Kafka;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class DemoSupplier {

  @Autowired
  @Qualifier("embeddedKafkaBroker")
  public EmbeddedKafkaBroker kafkaBroker;

  @Autowired
  private KafkaTemplate<String,String> kafkaTemplate;

  @Value("${demo.topic}")
  private String topicName;

  public void supply(){
    for(int i =0 ;i<100;i++){
      kafkaTemplate.send(topicName, "Message:"+i*2);
    }
  }

  @Value("${object.demo.topic}")
  private String messageTopicName;

  @Autowired
  private KafkaTemplate<String, Message> messageKafkaTemplate;
  @Bean
  public KafkaTemplate<String, Message> messageKafkaTemplateBean(){
    Map<String, Object> producerConfigs =new HashMap<>();
    producerConfigs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
    producerConfigs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    producerConfigs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfigs));
  }
  @Bean
  public KafkaTemplate<String, String> stringKafkaTemplate(){
    Map<String, Object> producerConfigs =new HashMap<>();
    producerConfigs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
    producerConfigs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    producerConfigs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
    return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfigs));
  }

  public void supplyMessages(){
    for(int i =0;i<100;i++){
      messageKafkaTemplate.send(messageTopicName,
          Message.builder()
              .name("name:"+i*2)
              .phone("phone:9"+i*73)
              .build());
    }
  }



}


