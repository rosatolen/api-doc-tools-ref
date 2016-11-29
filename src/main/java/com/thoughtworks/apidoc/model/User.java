package com.thoughtworks.apidoc.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.UUID;

public class User {

    private String name;
    private Integer id;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateUpdated;

    public User() {
        //mybatis
    }

    @JsonCreator
    public User(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateUpdated(OffsetDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public OffsetDateTime getDateUpdated() {
        return dateUpdated;
    }
}
