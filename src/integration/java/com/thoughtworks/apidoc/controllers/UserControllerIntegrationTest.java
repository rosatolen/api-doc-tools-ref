package com.thoughtworks.apidoc.controllers;


import com.thoughtworks.apidoc.APIReferenceApplication;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpStatus.CREATED;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = APIReferenceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @LocalServerPort
    int port;


    @Before
    public void setUp() {
        RestAssured.port = port;
    }


    @Test
    public void shouldCreateAUser() throws Exception {
        given()
                .body("{\"name\": \"Max\"}")
                .contentType(ContentType.JSON)
        .when()
                .post("/users")
        .then()
                .statusCode(CREATED.value())
                .body("name", is("Max"))
                .body("id", is(notNullValue()));
    }
}
