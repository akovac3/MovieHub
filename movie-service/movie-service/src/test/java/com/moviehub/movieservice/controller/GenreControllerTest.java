package com.moviehub.movieservice.controller;

import com.moviehub.movieservice.exception.ResourceNotFoundException;
import com.moviehub.movieservice.model.Actor;
import com.moviehub.movieservice.model.Genre;
import com.moviehub.movieservice.repository.ActorRepository;
import com.moviehub.movieservice.repository.GenreRepository;
import com.moviehub.movieservice.repository.MovieRepository;
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
class GenreControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private GenreRepository genreRepository;

    Genre g;

    @BeforeEach
    void setup() {
        genreRepository.deleteAll();
        g = new Genre("action");
        Genre g1 = new Genre("crime");
        Genre g2 = new Genre("comedy");
        genreRepository.save(g1);
        genreRepository.save(g2);
        genreRepository.save(g);
    }

    @Test
    void getAllGenres() throws Exception {
        mockMvc.perform(get("/genre/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("crime"))
                .andExpect(jsonPath("$.[1].name").value("comedy"));
    }

    @Test
    void addGenreSuccessfully() throws Exception {
        mockMvc.perform(post("/genre/")
                        .content("{\n" +
                                "\"name\": \"documentary\"," +
                                "\"movies\": []" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("Genre successfully added!"));
    }

    @Test
    void getGenre() throws Exception {
        mockMvc.perform(get(String.format("/genre/%d", g.getId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("action"));
    }

    @Test
    void deleteGenre() throws Exception {
        Long id = g.getId();
        mockMvc.perform(delete(String.format("/genre/%d", id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void deleteGenreNotFound() throws Exception {
        int id = 1000;
        mockMvc.perform(delete(String.format("/genre/%d", id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));
    }


    @Test
    void updateGenre() throws Exception {
        Long id = g.getId();
        mockMvc.perform(put(String.format("/genre/%d", id))
                        .content("{\n" +
                                "\"name\": \"adventure\"," +
                                "\"movies\": []" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Genre with id = " + id +" successfully updated!"));
    }

    @Test
    void patchGenre() throws Exception {
        Long id = g.getId();
        mockMvc.perform(patch(String.format("/genre/%d", id))
                        .content( "[ {\"op\":\"replace\",\"path\":\"/name\",\"value\":\"action\"}]")
                        .contentType("application/json-patch+json"))
                .andExpect(status().isOk())
                .andExpect(content().string("Genre with id = " + id +" successfully updated!"));
    }


}
