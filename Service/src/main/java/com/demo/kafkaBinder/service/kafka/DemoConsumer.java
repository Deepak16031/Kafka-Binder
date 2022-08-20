package com.demo.kafkaBinder.service.kafka;

import java.util.function.Consumer;

import com.demo.kafkaBinder.service.Model.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DemoConsumer {

  @Bean
  @Qualifier("demoConsumerProcessor2")
  public Consumer<KStream<String, String>> demoConsumerProcessor2(){
    return input -> input.foreach(((key, value) -> System.out.println("This is second consumer 2: "+value)));
  }
  @Bean
  @Qualifier("demoConsumerProcessor")
  public Consumer<KStream<String, String>> demoConsumerProcessor(){
    return input -> input.foreach(((key, value) -> System.out.println(value)));
  }

//  @KafkaListener(topics = "${demo.topic}",groupId = "demo_group")
//  public void listener(ConsumerRecord<String,String> record){
//    String value = record.value();
//  }

  @Bean
  @Qualifier("objectConsumerProcessor")
  public Consumer<KStream<String, Message>> objectConsumerProcessor(){
    return input -> input.foreach(((key, value) -> System.out.println("This is second consumer 2: "+value)));
  }
}
