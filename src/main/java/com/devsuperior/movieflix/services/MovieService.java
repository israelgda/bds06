package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService{
	
	@Autowired 
	private MovieRepository movieRepository;
	
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> movie = movieRepository.findById(id);
		MovieDTO movieDTO = new MovieDTO(movie.orElseThrow(()-> new ResourceNotFoundException("Movie Not Found. Id: " + id)));
	
		return movieDTO;
	}

	@Transactional(readOnly = true)
	public Page<MovieDTO> findByGenre(Long genreId, Pageable pageable) {
		genreId = (genreId == 0) ? null : genreId;
		Page<MovieDTO> page = movieRepository.findByGenre(genreId, pageable);
		return page;
	}
	
	
}
