package com.moviehub.movieservice;

import com.moviehub.movieservice.repository.ActorRepository;
import com.moviehub.movieservice.repository.GenreRepository;
import com.moviehub.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieServiceApplication implements CommandLineRunner{

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private ActorRepository actorRepository;
	@Autowired
	private GenreRepository genreRepository;

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}
	/*
        @Bean
        public Docket movieApi(){
            return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.moviehub.movieservice.controllers")).build();
        }*/
	@Override
	public void run(String... args) throws Exception {

		/*Movie first = new Movie(
				"Avengers: Endgame", (float) 8.4, "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
				2019);
		Movie second = new Movie("The Batman", (float)8.2, "When the Riddler, a sadistic serial killer, begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement.",
				2022);

		Actor rob = new Actor("Robert", "Pattinson");
		Actor zoe = new Actor("Zoe", "Kravitz");
		Actor jeff = new Actor("Jeffrey", "Wright");

		Genre action = new Genre("action");
		Genre crime = new Genre("crime");
		Genre drama = new Genre("drama");
		Genre advent = new Genre("adventure");

		first.getActors().add(rob);
		first.getActors().add(zoe);
		first.getActors().add(jeff);
		first.getGenres().add(action);
		first.getGenres().add(crime);
		first.getGenres().add(drama);

		Actor robert = new Actor("Robert", "Downey Jr.");
		Actor chris = new Actor("Chris", "Hemsworth");

		second.getActors().add(robert);
		second.getActors().add(chris);

		second.getGenres().add(advent);

		rob.getMovies().add(first);
		zoe.getMovies().add(first);
		jeff.getMovies().add(first);
		robert.getMovies().add(second);
		chris.getMovies().add(second);

		action.getMovies().add(first);
		crime.getMovies().add(first);
		drama.getMovies().add(first);
		advent.getMovies().add(second);

		this.movieRepository.save(first);
		this.movieRepository.save(second);
*/
	}
}
