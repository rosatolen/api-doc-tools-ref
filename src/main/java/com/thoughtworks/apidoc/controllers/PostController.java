package com.thoughtworks.apidoc.controllers;

import com.thoughtworks.apidoc.exceptions.NotFoundException;
import com.thoughtworks.apidoc.model.Post;
import com.thoughtworks.apidoc.resources.DefaultQueryParams;
import com.thoughtworks.apidoc.resources.PostCollection;
import com.thoughtworks.apidoc.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/posts", produces = APPLICATION_JSON_VALUE)
public class PostController {

    private PostService postService;
    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
        LOGGER.warn("ITS A WARNING MESSAGE FROM A CONSTRUCTOR##############");
    }

    @RequestMapping(method = GET)
    public ResponseEntity<PostCollection> collection(DefaultQueryParams params, Optional<String> authorId, @RequestParam Optional<List<Integer>> tings) {
        List<Post> posts = postService.getPosts(params.getSortKeys(), params.getPage(), params.getPerPage(), authorId.map(UUID::fromString));
        return new ResponseEntity<>(new PostCollection(posts), OK);
    }

    @ResponseStatus(CREATED)
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> create(@RequestBody Post post) {
        Post createdPost = postService.create(post);

        return new ResponseEntity<>(createdPost, CREATED);
    }

    @RequestMapping(path = "/{id}", method = GET)
    public ResponseEntity<Post> get(@PathVariable String id) {
        Post post = postService.get(id).orElseThrow(() -> new NotFoundException("Unable to find post with id " + id));
        return new ResponseEntity<>(post, OK);

    }

    @RequestMapping(path = "/{id}", method = DELETE)
    public ResponseEntity<Post> delete(@PathVariable String id) {
        Post post = postService.delete(id).orElseThrow(() -> new NotFoundException("Unable to find post with id " + id));
        return new ResponseEntity<>(post, OK);
    }

    @RequestMapping(path = "/{id}", method = PUT)
    public ResponseEntity<Post> update(@PathVariable String id) {
        Post post = postService.update(id).orElseThrow(() -> new NotFoundException("Unable to find post with id " + id));
        return new ResponseEntity<>(post, OK);
    }
}
