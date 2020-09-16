package org.tony.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tony.mapper.UserMapper;
import org.tony.model.User;

import java.util.List;

@Service
@CacheConfig(cacheNames = "user_cache")
public class UserService {
    private final UserMapper userMapper;
    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper=userMapper;
    }

//Not out-of-box support, will try later on my side.
//  https://github.com/spring-projects/spring-framework/issues/23221
//    https://github.com/neiser/spring-collection-cacheable
//    @Cacheable
    public List<User> getAllUsers(){
        return  userMapper.getAllUsers();
    }

//    @Cacheable(key = "#id")
    public User getUserById(int id){
        return userMapper.getUserById(id);
    }

//    @CachePut(key = "#user.id")
    public User addUser(User user){
        userMapper.addUser(user);
        return user;
    }

//    @CacheEvict(key = "#id")
    public void deleteUserById(int id){
        userMapper.deleteUserById(id);
    }

//    @CachePut(key = "#user.id")
    public User updateUserById(User user){
        userMapper.updateUserById(user);
        return user;
    }
//https://www.jianshu.com/p/4f834f0e2dbe
    @Transactional
    public void updateSalaryById(int id, int  salaryIncrease) throws InterruptedException{
        User user =userMapper.getUserByIdForUpdate(id);
        int targetSalary= user.getSalary()+salaryIncrease;
        if(salaryIncrease>0){
            Thread.sleep(10000);
        }
        userMapper.updateSalary(id, targetSalary);
    }

}
