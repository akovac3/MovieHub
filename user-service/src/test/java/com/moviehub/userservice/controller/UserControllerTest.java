package com.moviehub.userservice.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUsers() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/user/all")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addUser() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"newnew\",\n" +
                        "  \"lastName\": \"newnew\",\n" +
                        "  \"email\": \"test123@test123.com\",\n" +
                        "  \"password\": \"pass\",\n" +
                        "  \"username\": \"username\"\n" +
                        "}");

        mockMvc.perform(request)
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void getUser() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/user/18")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }
}