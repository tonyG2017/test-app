package org.tony.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String sayHello(){
        return "hello world";
    }
}
