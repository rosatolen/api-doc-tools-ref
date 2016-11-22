package com.thoughtworks.apidoc.swagger;

import com.thoughtworks.apidoc.APIReferenceApplication;
import io.github.robwin.swagger.test.SwaggerAssert;
import io.swagger.models.Swagger;
import io.swagger.parser.Swagger20Parser;
import io.swagger.parser.SwaggerParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {APIReferenceApplication.class, SwaggerConfiguration.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SwaggerDocTest {

    @Resource
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void serveAllExpectedAuthenticatedEndpoints() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/v2/api-docs"))
                .andDo(result -> {
                    String contentAsString = result.getResponse().getContentAsString();
                    Swagger actual = new Swagger20Parser().parse(contentAsString);
                    Swagger expected = new SwaggerParser().read("spec.json");
                    new SwaggerAssert(actual).isEqualTo(expected);
                });
    }

}
