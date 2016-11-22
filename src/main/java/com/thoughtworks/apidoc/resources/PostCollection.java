package com.thoughtworks.apidoc.resources;

import com.thoughtworks.apidoc.model.Post;

import java.util.List;

public class PostCollection {
    private List<Post> data;

    public PostCollection(List<Post> data) {
        this.data = data;
    }

    public List<Post> getData() {
        return data;
    }

    public void setData(List<Post> data) {
        this.data = data;
    }
}
