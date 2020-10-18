package org.tony.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tony.mapper.EsUserMapper;
import org.tony.model.User;

import java.util.List;

@Service
public class EsUserService {
    private  final EsUserMapper esUserMapper;
    @Autowired
    public EsUserService(EsUserMapper esUserMapper){
        this.esUserMapper = esUserMapper;
    }

    public void save(User user){
        esUserMapper.save(user);
    }

    public List<User> findByName(String name){
        List<User> users= esUserMapper.findByFullName(name);
        return users;
    }
}

