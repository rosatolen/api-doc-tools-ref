package com.thoughtworks.apidoc.controllers;

import com.thoughtworks.apidoc.resources.Message;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path = "/", produces = APPLICATION_JSON_VALUE)
public class SkeletonController {

    @RequestMapping(method = GET)
    public ResponseEntity<Message> hi() {
        return new ResponseEntity<>(new Message("HELLO"), OK);
    }
}
