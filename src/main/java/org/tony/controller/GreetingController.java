package org.tony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tony.model.Greeting;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.tony.config.TestAppConfiguration;

@RestController
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private  TestAppConfiguration testAppConfiguration;
    //Not work when adding final //private final TestAppConfiguration testAppConfiguration;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name") Optional<String> name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(testAppConfiguration.getTemplate(), name.orElse(testAppConfiguration.getDefaultName())));
    }
}