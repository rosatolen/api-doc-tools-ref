package com.thoughtworks.apidoc.service;

import com.thoughtworks.apidoc.mappers.UserMapper;
import com.thoughtworks.apidoc.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.time.ZoneOffset.UTC;

@Component
public class UserService {

    private final UserMapper userMapper;
    private JdbcTemplate jdbcTemplate;

    private final ConcurrentMap<UUID, User> users = new ConcurrentHashMap<>();

    @Autowired
    public UserService(UserMapper userMapper, JdbcTemplate jdbcTemplate) {
        this.userMapper = userMapper;
        this.jdbcTemplate = jdbcTemplate;
    }


    public User createUser(User user) {
        OffsetDateTime now = OffsetDateTime.now(UTC);
        user.setDateCreated(now);
        user.setDateUpdated(now);
        userMapper.createUser(user);
        return user;
    }
    public List<User> getAllUsers(int page, int perPage) {
        int limit = page * perPage;
        int offset = limit - perPage;
        return userMapper.getAllUsers(new RowBounds(offset, limit));
    }
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUser(Integer id) {
        return userMapper.getUserById(id);
    }
}
