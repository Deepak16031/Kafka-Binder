package com.demo.kafkaBinder.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@ComponentScan(basePackages = {"com.demo.kafkaBinder"})
@EntityScan(basePackages = {"com.demo.kafkaBinder"})
public class ServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceApplication.class, args);
  }
}
