package org.tony.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "HelloService")
public class HelloService {
    @Cacheable
    public String sayHello(){
        return "hello world";
    }
}
