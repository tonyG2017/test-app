package org.tony.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tony.mapper.UserMapper;
import org.tony.model.User;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;
    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    public List<User> getAllUsers(){
        return  userMapper.getAllUsers();
    }

    public User getUserById(int id){
        return userMapper.getUserById(id);
    }

    public  int addUser(User user){
        return  userMapper.addUser(user);
    }

    public void deleteUserById(int id){
        userMapper.deleteUserById(id);
    }

    public void updateUserById(User user){
        userMapper.updateUserById(user);
    }
}
