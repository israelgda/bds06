package com.devsuperior.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	@Query(value = "SELECT obj "
			+ "FROM Movie obj "
			+ "WHERE (:genreId IS NULL OR obj.genre.id = :genreId) "
			+ "ORDER BY obj.title")
	Page<MovieDTO> findByGenre(Long genreId, Pageable pageable);

}
