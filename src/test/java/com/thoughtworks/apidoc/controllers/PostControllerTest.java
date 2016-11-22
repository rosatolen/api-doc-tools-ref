package com.thoughtworks.apidoc.controllers;


import com.thoughtworks.apidoc.model.Post;
import com.thoughtworks.apidoc.resources.PostCollection;
import com.thoughtworks.apidoc.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {

    @Mock
    PostService postService;
    @InjectMocks
    PostController controller;

    @Test
    public void shouldReturnAllPostsFromService() {
        List<Post> posts = asList(new Post("Title 1", "body 1"));
        when(postService.getAllPosts()).thenReturn(posts);

        ResponseEntity<PostCollection> response = controller.collection();

        assertThat(response.getBody(), is(posts));
    }

}