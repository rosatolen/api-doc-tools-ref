package com.thoughtworks.apidoc.service;

import com.thoughtworks.apidoc.mappers.PostMapper;
import com.thoughtworks.apidoc.resources.DefaultQueryParams;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static com.thoughtworks.apidoc.resources.DefaultQueryParams.PER_PAGE_DEFAULT;
import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Mock
    PostMapper mapper;

    @InjectMocks
    PostService postService;

    @Test
    public void shouldGetPaginatedPostsForAllUsersIfAuthorUnspecified() {
        DefaultQueryParams params = new DefaultQueryParams();
        RowBounds expectedRowBounds = new RowBounds(0, PER_PAGE_DEFAULT);

        postService.getPosts(Optional.empty(), params);

        verify(mapper).getAllPosts(refEq(expectedRowBounds));
    }

    @Test
    public void shouldConvertParamsToLimitOffsetCorrectly() {
        DefaultQueryParams params = new DefaultQueryParams();
        params.setPage(2);
        RowBounds expectedRowBounds = new RowBounds(PER_PAGE_DEFAULT, PER_PAGE_DEFAULT);//offset by one perPage size for page 2

        postService.getPosts(Optional.empty(), params);

        verify(mapper).getAllPosts(refEq(expectedRowBounds));
    }

    @Test
    public void shouldGetPostsForAuthorIfAuthorSpecified() {
        DefaultQueryParams params = new DefaultQueryParams();
        RowBounds expectedRowBounds = new RowBounds(0, PER_PAGE_DEFAULT);
        Integer authorId = 1;

        postService.getPosts(Optional.of(authorId), params);

        verify(mapper).getPostsForAuthor(eq(authorId), refEq(expectedRowBounds));
    }

}