package com.moviehub.movieservice.controller;

import com.moviehub.movieservice.exception.ResourceNotFoundException;
import com.moviehub.movieservice.model.Actor;
import com.moviehub.movieservice.model.Genre;
import com.moviehub.movieservice.model.Movie;
import com.moviehub.movieservice.repositorie.ActorRepository;
import com.moviehub.movieservice.repositorie.GenreRepository;
import com.moviehub.movieservice.repositorie.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ActorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private GenreRepository genreRepository;

    Actor a;

    @BeforeEach
    void setup() {
        actorRepository.deleteAll();
        a = new Actor("Robert", "Pattinson");
        Actor a1 = new Actor("Zoe", "Kravitz");
        actorRepository.save(a1);
        actorRepository.save(a);
    }

    @Test
    void getAllActors() throws Exception {
        mockMvc.perform(get("/actor/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].firstName").value("Zoe"))
                .andExpect(jsonPath("$.[1].firstName").value("Robert"));
    }

    @Test
    void addActorSuccessfully() throws Exception {
        mockMvc.perform(post("/actor/")
                        .content("{\n" +
                                "\"firstName\": \"Jeffrey\"," +
                                "\"lastName\": \"Wright\"" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("Actor successfully added!"));
    }

    @Test
    void getActor() throws Exception {
        mockMvc.perform(get(String.format("/actor/%d", a.getId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Robert"));
    }

    @Test
    void deleteActor() throws Exception {
        Long id = a.getId();
        mockMvc.perform(delete(String.format("/actor/%d", id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Actor successfully deleted!"));;

    }

    @Test
    void deleteActorNotFound() throws Exception {
        int id = 1000;
        mockMvc.perform(delete(String.format("/actor/%d", id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));
    }


    @Test
    void updateActor() throws Exception {
        Long id = a.getId();
        mockMvc.perform(put(String.format("/actor/%d", id))
                        .content("{\n" +
                                "\"firstName\": \"Leonardo\"," +
                                "\"lastName\": \"DiCaprio\"" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Actor with id = " + id +" successfully updated!"));
    }

    @Test
    void patchActor() throws Exception {
        Long id = a.getId();
        mockMvc.perform(patch(String.format("/actor/%d", id))
                        .content( "[ {\"op\":\"replace\",\"path\":\"/firstName\",\"value\":\"Chris\"}]")
                        .contentType("application/json-patch+json"))
                .andExpect(status().isOk())
                .andExpect(content().string("Actor with id = " + id +" successfully updated!"));
    }


}
