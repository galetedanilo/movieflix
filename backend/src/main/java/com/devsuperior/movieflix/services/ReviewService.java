package com.devsuperior.movieflix.services;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.DatabaseException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	private void copyDtoToEntity(ReviewDTO dto, Review entity) {
		entity.setText(dto.getText());
		entity.setMovie(movieRepository.getById(dto.getMovie().getId()));
	}
	
	@Transactional(readOnly = true)
	public Page<ReviewDTO> findAll(Pageable pageable) {
		
		Page<Review> page = repository.findAll(pageable);
		
		return page.map(x -> new ReviewDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ReviewDTO findById(Long id) {
		
		Optional<Review> optional = repository.findById(id);
		
		Review entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
		
		return new ReviewDTO(entity);
	}
	
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		
		try {
			Review entity = new Review();
			
			copyDtoToEntity(dto, entity);
			
			entity = repository.save(entity);
			
			return new ReviewDTO(entity);
		}catch(DataIntegrityViolationException ex) {
			throw new DatabaseException("Constraint violation.");
		}
	}
	
	@Transactional
	public ReviewDTO update(Long id, ReviewDTO dto) {
		
		try {
			Review entity = repository.getById(id);
			
			copyDtoToEntity(dto, entity);
			
			entity = repository.save(entity);
			
			return new ReviewDTO(entity);
		}catch(EntityNotFoundException ex) {
			throw new ResourceNotFoundException("Entity not found " + id + ".");
		}catch(DataIntegrityViolationException ex) {
			throw new DatabaseException("Integrity violation.");
		}
	}
	
	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("Entity not found " + id + ".");
		}catch(DataIntegrityViolationException ex) {
			throw new DatabaseException("Integrity violation.");
		}
	}
}
