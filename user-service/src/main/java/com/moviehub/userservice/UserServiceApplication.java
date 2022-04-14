package com.moviehub.userservice;

import com.moviehub.userservice.model.Role;
import com.moviehub.userservice.model.User;
import com.moviehub.userservice.repository.UserRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate () { return new RestTemplate(); }

	@Override
	public void run(String... args) throws Exception {
		//User user1 = new User("neko", "nekic", "nnekic1@etf.unsa.ba", "password", "nnekic1", Role.ROLE_USER);
		//this.userRepository.save(user1);
	}
}
