package com.thoughtworks.apidoc.controllers;


import com.thoughtworks.apidoc.APIReferenceApplication;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpStatus.CREATED;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"local", "test"})
@SpringBootTest(classes = APIReferenceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerIntegrationTest {

    @LocalServerPort
    private int port;


    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void shouldCreateAPost() throws Exception {
        given()
                .body("{\"title\": \"Title\", \"body\": \"body\"}")
                .contentType(ContentType.JSON)
        .when()
                .post("/posts")
        .then()
                .statusCode(CREATED.value())
                .body("title", is("Title"))
                .body("body", is("body"))
                .body("id", is(notNullValue()));
    }
}
