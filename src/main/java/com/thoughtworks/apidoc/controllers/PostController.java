package com.thoughtworks.apidoc.controllers;

import com.thoughtworks.apidoc.model.Post;
import com.thoughtworks.apidoc.resources.PostCollection;
import com.thoughtworks.apidoc.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static javax.servlet.http.HttpServletResponse.SC_CREATED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/posts", produces = APPLICATION_JSON_VALUE)
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(method = GET)
    public ResponseEntity<PostCollection> collection() {
        return new ResponseEntity<>(new PostCollection(postService.getAllPosts()), OK);
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
