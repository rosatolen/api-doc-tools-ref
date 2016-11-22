package com.thoughtworks.apidoc.service;

import com.thoughtworks.apidoc.model.Post;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;


public class PostServiceTest {

    private PostService postService = new PostService();

    @Test
    public void shouldReturnEmptyListWhenFreshlyInitialized() {
        List<Post> allPosts = postService.getAllPosts();
        assertThat(allPosts, empty());
    }

    @Test
    public void shouldSuccessfullyAddAndReturnSinglePost() {
        String title = "A Post";
        String body = "body";
        Post post = new Post(title, body);

        Post savedPost = postService.create(post);

        assertThat(savedPost.getBody(), is(body));
        assertThat(savedPost.getTitle(), is(title));

        assertThat(postService.getAllPosts(), hasItem(savedPost));
    }

    @Test
    public void shouldRetrieveExistingPost() {
        Post expected = postService.create(new Post("title", "body"));

        UUID id = expected.getId();
        Optional<Post> actual = postService.get(id.toString());
        assertThat(actual.get(), is(expected));
    }

    @Test
    public void shouldReturnNoneForNonexistentPost() {
        Optional<Post> post = postService.get("foo");
        assertThat(post.isPresent(), is(false));
    }

}