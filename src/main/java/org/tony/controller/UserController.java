package org.tony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tony.model.User;
import org.tony.service.UserService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //https://www.baeldung.com/spring-requestmapping
    @GetMapping(value = "/{id:\\d{1,5}}")
    public User getUserById(@PathVariable int id){
        return  userService.getUserById(id);
    }

    //https://www.baeldung.com/building-a-restful-web-service-with-spring-and-java-based-configuration
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user ){
        userService.addUser(user);
        return user;
    }

    @DeleteMapping(value = "/{id:\\d{1,5}}")
    public void deleteUserById(@PathVariable int id){
        userService.deleteUserById(id);
    }

    @PutMapping(value = "/{id:\\d{1,5}}")
    public void updateUserById(@PathVariable( "id" ) int id, @RequestBody User user){
        System.out.println("*****user:"+user);
        user.setId(id);
        System.out.println("*****user after:"+user);
        userService.updateUserById(user);
    }
}
