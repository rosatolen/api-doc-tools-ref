package com.thoughtworks.apidoc.mappers;

import com.thoughtworks.apidoc.model.Post;
import com.thoughtworks.apidoc.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.UUID;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS")
    List<User> getAllUsers(RowBounds bounds);

    @Insert("INSERT INTO USERS (name, dateCreated, dateUpdated) VALUES (#{name}, #{dateCreated}, #{dateUpdated})")
    @Options(useGeneratedKeys = true)
    void createUser(User user);

    @Select("SELECT * FROM USERS WHERE id = #{id}")
    User getUserById(Integer id);
}
