package com.eashurov.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.eashurov.moviecatalogservice.models.CatalogItem;
import com.eashurov.moviecatalogservice.models.Movie;
import com.eashurov.moviecatalogservice.models.Rating;
import com.eashurov.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping ("/catalog")
public class MovieCatalogResources {
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private WebClient.Builder webClientbuilder;

	
	
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		
//		List<Rating> ratings = Arrays.asList(
//				new Rating("NewMovie", 3),
//				new Rating("SecondMovie", 4),
//				new Rating("ThirdMovie", 4)
//				);
//		
		
		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);
		return ratings.getUserRating().stream().map(rating ->{
			
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
			
//		Movie movie = webClientbuilder.build()
//			.get()
//			.uri("http://localhost:8082/movies/"+rating.getMovieId())
//			.retrieve()
//			.bodyToMono(Movie.class)
//			.block();
//			
			
			
			return new CatalogItem(movie.getName(), "Test", rating.getRating());
			
		}).collect(Collectors.toList());
			
		
		
		
	
	}
}
