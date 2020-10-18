package org.tony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tony.exceptions.UserNotFoundException;
import org.tony.model.User;
import org.tony.service.EsUserService;
import org.tony.service.UserService;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@RestController
@RequestMapping("/ES/users")
public class EsUserController {
    private final EsUserService esUserService;

    @Autowired
    public EsUserController(EsUserService esUserService){
        this.esUserService = esUserService;
    }

    @GetMapping(value = "/{name}")
    public List<User> getUserByFullName(@PathVariable  String name){
        return esUserService.findByName(name);
    }

    @PostMapping
    public void getUserById(@RequestBody User user){
         esUserService.save(user);
    }
}
