package org.tony.mapper;


import org.tony.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface EsUserMapper  extends ElasticsearchRepository<User, String> {
//    https://www.cnblogs.com/javawxid/p/10966814.html
    List<User> findByFullName(String name);
    User save(User user);
}


