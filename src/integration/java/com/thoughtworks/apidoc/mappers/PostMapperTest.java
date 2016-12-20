package com.thoughtworks.apidoc.mappers;

import com.thoughtworks.apidoc.APIReferenceApplication;
import com.thoughtworks.apidoc.model.Post;
import com.thoughtworks.apidoc.resources.SortKey;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"mapper", "test", "local"})
@SpringBootTest(classes = {APIReferenceApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PostMapperTest {

    @Autowired
    PostMapper postMapper;

    @Test
    public void shouldFoo() {
        Post post = new Post("A POST", "HELLO!");
        post.setAuthorId(1);
        postMapper.createPost(post);

        List<Post> postsForAuthor = postMapper.getPostsForAuthor(1, new RowBounds(0, 5));

        assertThat(postsForAuthor, everyItem(hasProperty("authorId", is(1))));
    }

    @Test
    public void shouldSortThingsCorrectly() {
        List<SortKey> keys = Arrays.asList(SortKey.fromString("-title"));
        List<Post> allPostsWithOrder = postMapper.getAllPostsWithOrder(keys, new RowBounds());
        System.out.println(allPostsWithOrder.stream().map(Post::getTitle).collect(toList()));
    }
}
