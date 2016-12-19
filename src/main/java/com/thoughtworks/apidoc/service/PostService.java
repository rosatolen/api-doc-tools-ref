package com.thoughtworks.apidoc.service;

import com.thoughtworks.apidoc.mappers.PostMapper;
import com.thoughtworks.apidoc.resources.DefaultQueryParams;
import com.thoughtworks.apidoc.model.Post;
import com.thoughtworks.apidoc.validation.UUIDValidator;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.time.OffsetDateTime.now;
import static java.time.ZoneOffset.UTC;

@Service
public class PostService {

    private final ConcurrentMap<String, Post> posts = new ConcurrentHashMap<>();
    final JdbcTemplate jdbcTemplate;
    private PostMapper postMapper;
    private UserService userService;

    @Autowired
    public PostService(JdbcTemplate jdbcTemplate, PostMapper postMapper, UserService userService) {
        this.jdbcTemplate = jdbcTemplate;
        this.postMapper = postMapper;
        this.userService = userService;
    }

    public List<Post> getPosts(Optional<Integer> authorId, DefaultQueryParams params) {
        int offset = (params.getPage() - 1) * params.getPerPage();
        RowBounds bounds = new RowBounds(offset, params.getPerPage());
        if (authorId.isPresent()) {
            return postMapper.getPostsForAuthor(authorId.get(), bounds);
        } else {
            return postMapper.getAllPosts(bounds);
        }
    }


    private boolean exceedsLastPage(int offset) {
        return offset >= posts.size();
    }


    //yes yes, I'm terrible, mutating params
    public Post create(Post post) {

        OffsetDateTime now = now(UTC);
        post.setDateCreated(now);
        post.setDateUpdated(now);
        postMapper.createPost(post);
        post.setAuthor(userService.getUser(post.getId()));
        return post;
    }

    public Optional<Post> get(String id) {
        UUIDValidator validator = new UUIDValidator();
        if (!validator.validate(id)) {return Optional.empty();}
        return Optional.ofNullable(postMapper.getPostById(Integer.valueOf(id)));
    }

    public Optional<Post> delete(String id) {
        return Optional.ofNullable(posts.remove(id));
    }

    public Optional<Post> update(String id) {
        Optional<Post> post = Optional.ofNullable(posts.get(id));
        post.ifPresent(x -> x.setDateUpdated(now(UTC)));
        return post;
    }
}
