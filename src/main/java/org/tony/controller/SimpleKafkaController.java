package org.tony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tony.config.TestAppConfiguration;
import org.tony.model.Greeting;
import org.tony.service.SimpleKafkaService;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class SimpleKafkaController {

    private final SimpleKafkaService simpleKafkaService;
    @Autowired
    public SimpleKafkaController(SimpleKafkaService simpleKafkaService){
        this.simpleKafkaService = simpleKafkaService;
    }

    @RequestMapping(value = "/simple-kafka-events", method = GET )  //If we ignore method, all of the methods, i.e., GET/POST/DELETE show in the swagger ui.
    public void greeting(@RequestParam(value="topic") String topic,@RequestParam(value="message") String message ) {
        simpleKafkaService.sendMessage(topic, message);
        //Adding lombok to pom.xml makes it works for runtime. Only if installing lombok plugin in Intellij,  can we see the  getter/setter directly.
        //https://www.baeldung.com/lombok-ide
    }
}