package org.tony.mapper;

import org.apache.ibatis.annotations.*;
import org.tony.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM people")
    List<User> getAllUsers();

    @Select("SELECT * FROM people WHERE id=#{id}")
    User getUserById(int id);

    @Select("SELECT * FROM people WHERE id=#{id} for update")
    User getUserByIdForUpdate(int id);

    @Insert("INSERT INTO people(fullName, jobTitle, password) VALUES (#{fullName}, #{jobTitle}, #{password})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    //https://www.cnblogs.com/Weagle/p/5354357.html
    //https://stackoverflow.com/questions/4283159/how-to-return-ids-on-inserts-with-mybatis-in-mysql-with-annotations
    //Return the table's AUTO_INCREMENT id and update the bean with it.
    int addUser(User user);

    @Delete("DELETE FROM people WHERE id=#{id}")
    int deleteUserById(int id);

    @Update("UPDATE people set fullName=#{fullName}, jobTitle=#{jobTitle}, password=#{password} WHERE id=#{id}")
    int updateUserById(User user);

    @Update("UPDATE people set salary=#{salary} WHERE id=#{id}")
    int updateSalary( long id, int salary);

}
