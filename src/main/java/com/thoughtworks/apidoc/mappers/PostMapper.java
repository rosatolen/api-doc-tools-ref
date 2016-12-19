package com.thoughtworks.apidoc.mappers;

import com.thoughtworks.apidoc.model.Post;
import com.thoughtworks.apidoc.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Mapper
@Component
public interface PostMapper {

    @Select("SELECT posts.title, posts.body, posts.id, posts.dateCreated, posts.dateUpdated, users.name as authorName FROM posts JOIN users ON posts.authorId = users.id")
//    @Results({@Result(property = "author", column = "post.authorId", javaType = User.class)})
    List<Post> getAllPosts(RowBounds bounds);

    @Insert("INSERT INTO POSTS (title, body, dateCreated, dateUpdated, authorId) VALUES (#{title}, #{body}, #{dateCreated}, #{dateUpdated}, #{authorId})")
    @Options(useGeneratedKeys = true)
    void createPost(Post post);

    @Select("SELECT posts.title, posts.body, posts.id, posts.dateCreated, posts.dateUpdated, users.name FROM POSTS JOIN USERS ON posts.authorId = users.id WHERE posts.id = #{id}")
    Post getPostById(Integer id);

    @Select("SELECT posts.title, posts.body, posts.id, posts.dateCreated, posts.dateUpdated, posts.authorId, users.name as authorName FROM posts JOIN users ON posts.authorId = users.id WHERE posts.authorId = #{authorId}")
    List<Post> getPostsForAuthor(Integer authorId, RowBounds expectedRowBounds);

}
