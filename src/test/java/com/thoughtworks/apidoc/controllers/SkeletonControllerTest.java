package com.thoughtworks.apidoc.controllers;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SkeletonControllerTest {

    private final SkeletonController skeletonController = new SkeletonController();

    @Test
    public void shouldReturnHello() {
//        ResponseEntity<String> hi = skeletonController.hi();
//
//        assertThat(hi.getStatusCode(), is(HttpStatus.OK));
//        assertThat(hi.getBody(), is("HELLO"));
    }

}