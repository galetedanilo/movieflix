package com.devsuperior.movieflix.services;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieFullDTO;
import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exception.ResourceNotFoundException;

@Service
public class MovieService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MovieRepository repository;
	
	@Transactional(readOnly = true)
	public Page<MovieMinDTO> findAll(Long genre, Pageable pageable) {

		Page<Movie> page = repository.findAllByGenre(genre, pageable);
		
		return page.map(x -> new MovieMinDTO(x));
	}
	
	@Transactional(readOnly = true)
	public MovieFullDTO findById(Long id) {
		
		Optional<Movie> optional = repository.findById(id);
		
		Movie entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
		
		return new MovieFullDTO(entity);		
	}
}
