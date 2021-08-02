package com.devsuperior.movieflix.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewMinDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired 
	private MovieRepository movieRepository;
	
	@Autowired
	private UserService userService;
	
	private void copyDtoToEntity(ReviewMinDTO dto, Review entity) {
		entity.setText(dto.getText());
		
		entity.setUser(userService.authenticated());
		
		entity.setMovie(movieRepository.getById(dto.getMovieId()));
		
	}
	
	@Transactional
	public ReviewMinDTO insert(ReviewMinDTO dto) {
		
		Review entity = new Review();
		
		copyDtoToEntity(dto, entity);
		
		entity = repository.save(entity);
		
		return new ReviewMinDTO(entity);
	}
}
