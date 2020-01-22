package com.eashurov.ratingdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eashurov.ratingdataservice.models.Rating;
import com.eashurov.ratingdataservice.models.UserRating;

@RestController
@RequestMapping ("/ratingsdata")
public class RatingsResource {
	
	
	@RequestMapping("/movies/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		
		return new Rating(movieId, 4);
		
		
		
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		
//		
//		List<Rating> ratings = Arrays.asList(
//				new Rating("NewMovie", 3),
//				new Rating("SecondMovie", 4),
//				new Rating("ThirdMovie", 4)
//				);
		
		UserRating userRating = new UserRating();
		userRating.initData(userId);
		return userRating;
		
		
		
	}
	

}
