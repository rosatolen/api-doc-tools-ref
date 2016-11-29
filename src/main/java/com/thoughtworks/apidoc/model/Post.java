package com.thoughtworks.apidoc.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

public class Post {
    private String title;

    private String body;
    @JsonProperty(access = READ_ONLY)
    private User author;
    @JsonProperty(access = WRITE_ONLY)
    private Integer authorId;
    @JsonProperty(access = READ_ONLY)
    private Integer id;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateUpdated;
    private String authorName;

    public Post() {
        //for mybatis
    }

    @JsonCreator
    public Post(@JsonProperty("title") String title, @JsonProperty("body") String body) {

        this.title = title;
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(OffsetDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
