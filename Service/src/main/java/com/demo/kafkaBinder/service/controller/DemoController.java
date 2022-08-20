package com.demo.kafkaBinder.service.controller;

import com.demo.kafkaBinder.service.kafka.DemoSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/demo/")
public class DemoController {

  @Autowired
  DemoSupplier demoSupplier;

  @GetMapping("hello")
  public String helloController(){
    demoSupplier.supply();
    return "Hello World!";
  }
  @GetMapping("object")
  public String objectController(){
    demoSupplier.supplyMessages();
    return "Hello Objects!";
  }

}
