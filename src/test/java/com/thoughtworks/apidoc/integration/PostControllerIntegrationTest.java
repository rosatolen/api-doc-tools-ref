package com.thoughtworks.apidoc.integration;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import com.thoughtworks.apidoc.APIReferenceApplication;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = APIReferenceApplication.class, properties = "server.port:0")
@WebAppConfiguration
public class PostControllerIntegrationTest {

    @Value("${server.port}")
    int port;


    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void shouldSayHello() throws Exception {
        when()
                .get("/")
        .then()
                .statusCode(OK.value())
                .body("content", is("HELLO"));
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
