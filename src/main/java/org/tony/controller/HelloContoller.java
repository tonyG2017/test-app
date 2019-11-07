package org.tony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tony.service.HelloService;

@RestController
public class HelloContoller {
    private final HelloService helloService;
    @Autowired
    public HelloContoller(HelloService helloService){
        this.helloService = helloService;
    }
    @GetMapping("hello")
    public String hello(){
        return helloService.sayHello();
    }
}
