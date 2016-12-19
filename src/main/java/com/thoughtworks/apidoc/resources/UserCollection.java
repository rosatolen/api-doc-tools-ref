package com.thoughtworks.apidoc.resources;

import com.thoughtworks.apidoc.model.Post;
import com.thoughtworks.apidoc.model.User;

import java.util.List;

public class UserCollection {
    private List<User> data;

    public UserCollection(List<User> data) {
        this.data = data;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
