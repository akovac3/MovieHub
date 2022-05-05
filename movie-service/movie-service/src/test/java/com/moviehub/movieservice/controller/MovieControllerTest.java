package com.moviehub.movieservice.controller;

import com.moviehub.movieservice.exception.ResourceNotFoundException;
import com.moviehub.movieservice.model.Actor;
import com.moviehub.movieservice.model.Genre;
import com.moviehub.movieservice.model.Movie;
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
class MovieControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private GenreRepository genreRepository;

    Movie m;

    @BeforeEach
    void setup() {
        m = new Movie("Avengers: Endgame", (float) 8.4, "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
                2019, 1L);
        movieRepository.save(m);
    }

    @Test
    void getAllMovies() throws Exception {
        mockMvc.perform(get("/api/movie/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title").value("Avengers: Endgame"));
    }

    @Test
    void addMovieSuccessfully() throws Exception {
        mockMvc.perform(post("/api/movie/")
                        .content("{\n" +
                                "\"title\": \"The Batman\"," +
                                "\"grade\": 8.2," +
                                "\"description\": \"When the Riddler, a sadistic serial killer, begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement.\","+
                                "\"year\": 2022," +
                                "\"userId\": 1" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("Movie successfully added!"));
    }

    @Test
    void addMovieUnsuccessfully() throws Exception {
        mockMvc.perform(post("/api/movie/")
                        .content("{\n" +
                                "\"title\": \"Titanic\"," +
                                "\"grade\": 7.9," +
                                "\"description\": \"A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic..\","+
                                "\"year\": 1997," +
                                "\"userId\": 2" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));
    }

    @Test
    void getMovie() throws Exception {
        mockMvc.perform(get(String.format("/api/movie/%d", m.getId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Avengers: Endgame"));
    }

    @Test
    void deleteMovie() throws Exception {
        Long id = m.getId();
        mockMvc.perform(delete(String.format("/api/movie/%d", id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Movie successfully deleted!"));;

    }

    @Test
    void deleteMovieNotFound() throws Exception {
        int id = 1000;
        mockMvc.perform(delete(String.format("/api/movie/%d", id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));
    }


    @Test
    void updateMovie() throws Exception {
        Long id = m.getId();
        mockMvc.perform(put(String.format("/api/movie/%d", id))
                        .content("{\n" +
                                "\"title\": \"Avengers\"," +
                                "\"grade\": 8.1," +
                                "\"description\": \"After the devastating events of Avengers: Infinity War (2018), the universe is in ruins.\","+
                                "\"year\": 2023" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Movie with id = " + id +" successfully updated!"));
    }

    @Test
    void patchMovie() throws Exception {
        Long id = m.getId();
        mockMvc.perform(patch(String.format("/api/movie/%d", id))
                        .content( "[ {\"op\":\"replace\",\"path\":\"/title\",\"value\":\"Endgame\"}]")
                        .contentType("application/json-patch+json"))
                .andExpect(status().isOk())
                .andExpect(content().string("Movie with id = " + id +" successfully updated!"));
    }


}
