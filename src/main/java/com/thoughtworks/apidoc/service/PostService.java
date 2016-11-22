package com.thoughtworks.apidoc.service;

import com.thoughtworks.apidoc.model.Post;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.time.OffsetDateTime.now;

@Service
public class PostService {

    private final ConcurrentMap<String, Post> posts = new ConcurrentHashMap<>();
    private static final ZoneId UTC = ZoneId.of("UTC");

    public List<Post> getAllPosts() {
        return new ArrayList<>(posts.values());
    }

    //yes yes, I'm terrible, mutating params
    public Post create(Post post) {
        UUID id;
        do {
            id = UUID.randomUUID();
        } while (posts.containsKey(id));
        post.setId(id);
        OffsetDateTime now = now(UTC);
        post.setDateCreated(now);
        post.setDateUpdated(now);
        posts.put(id.toString(), post);
        return post;
    }

    public Optional<Post> get(String id) {
        return Optional.ofNullable(posts.get(id));
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
