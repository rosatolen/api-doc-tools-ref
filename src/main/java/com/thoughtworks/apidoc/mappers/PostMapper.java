package com.thoughtworks.apidoc.mappers;

import com.thoughtworks.apidoc.model.Post;
import com.thoughtworks.apidoc.model.User;
import com.thoughtworks.apidoc.resources.SortKey;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

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

    @SelectProvider(type = PostSqlBuilder.class, method="buildGetAllWithOrder")
    List<Post> getAllPostsWithOrder(@Param("sortKeys") List<SortKey> sortKeys, RowBounds bounds);

    @Delete("DELETE FROM posts WHERE id = #{id}")
    Optional<Post> deletePost(Integer id);


    class PostSqlBuilder {
        private static final Set<String> VALID_KEYS = new HashSet<>(asList("title", "dateCreated", "dateUpdated", "authorId"));

        public String buildGetAllWithOrder(@Param("sortKeys")List<SortKey> sortKeys) {
            SQL sql = new SQL()
                    .SELECT("p.title, p.body, p.id, p.dateCreated, p.dateUpdated, p.authorId, u.name")
                    .FROM("POSTS p").JOIN("USERS u on p.authorId = u.id");
            for(SortKey sortKey : sortKeys.stream().filter(x -> VALID_KEYS.contains(x.key)).collect(toList())) {
                sql = sql.ORDER_BY(format("p.%s %s", sortKey.key, sortKey.ascending ? "asc" : "desc"));
            }
            return sql.toString();
        }
    }
}
