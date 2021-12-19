package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService{
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired 
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;
	
	
	@Transactional
	public ReviewDTO create(ReviewDTO reviewDTO) {
		Review review = dtoToReview(reviewDTO);
		review.setUser(authService.authenticated());
		
		review = repository.save(review);
		
		return new ReviewDTO(review);
		
	}


	private Review dtoToReview(ReviewDTO reviewDTO) {
		Review review = new Review();
		
		review.setText(reviewDTO.getText());
		review.setMovie(movieRepository.getOne(reviewDTO.getMovieId()));
		
		return review;
	}
	
	

}
