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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import com.eashurov.moviecatalogservice.models.CatalogItem;
import com.eashurov.moviecatalogservice.models.Movie;
import com.eashurov.moviecatalogservice.models.Rating;
import com.eashurov.moviecatalogservice.models.UserRating;
import com.eashurov.moviecatalogservice.services.MovieInfo;
import com.eashurov.moviecatalogservice.services.UserRatingInfo;

@RestController
@RequestMapping ("/catalog")
public class MovieCatalogResources {
	
	@Autowired
	WebClient.Builder webClientbuilder;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo userRatingInfo;
	
	
	
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		UserRating userRating = userRatingInfo.getUserRating(userId);
		return userRating.getRatings().stream()
				.map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());
		
		
//		List<Rating> ratings = Arrays.asList(
//				new Rating("NewMovie", 3),
//				new Rating("SecondMovie", 4),
//				new Rating("ThirdMovie", 4)
//				);
//				
//		Movie movie = webClientbuilder.build()
//			.get()
//			.uri("http://localhost:8082/movies/"+rating.getMovieId())
//			.retrieve()
//			.bodyToMono(Movie.class)
//			.block();
	
	}
	
	
	
//	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId){
//		System.out.println("************************* In Fallback method");
//		return Arrays.asList(new CatalogItem("No movie", "", 0));
//	}
		
	
}
