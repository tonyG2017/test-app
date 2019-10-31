package org.tony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tony.model.Greeting;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.tony.config.TestAppConfiguration;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private  TestAppConfiguration testAppConfiguration;
    //Not work when adding final //private final TestAppConfiguration testAppConfiguration;

    @RequestMapping(value = "/greeting", method = GET )  //If we ignore method, all of the methods, i.e., GET/POST/DELETE show in the swagger ui.
    public Greeting greeting(@RequestParam(value="name") Optional<String> name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(testAppConfiguration.getTemplate(), name.orElse(testAppConfiguration.getDefaultName())));
        //Adding lombok to pom.xml makes it works for runtime. Only if installing lombok plugin in Intellij,  can we see the  getter/setter directly.
        //https://www.baeldung.com/lombok-ide
    }
}