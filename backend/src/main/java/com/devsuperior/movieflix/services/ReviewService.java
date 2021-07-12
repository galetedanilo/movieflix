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

import com.devsuperior.movieflix.dto.ReviewFullDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.DatabaseException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private void copyDtoToEntity(ReviewFullDTO dto, Review entity) {
		entity.setText(dto.getText());
		entity.setMovie(movieRepository.getById(dto.getMovie().getId()));
		entity.setUser(userRepository.getById(dto.getUser().getId()));
	}
	
	@Transactional(readOnly = true)
	public Page<ReviewFullDTO> findAll(Pageable pageable) {
		
		Page<Review> page = repository.findAll(pageable);
		
		return page.map(x -> new ReviewFullDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ReviewFullDTO findById(Long id) {
		
		Optional<Review> optional = repository.findById(id);
		
		Review entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
		
		return new ReviewFullDTO(entity);
	}
	
	@Transactional
	public ReviewFullDTO insert(ReviewFullDTO dto) {
		
		try {
			Review entity = new Review();
			
			copyDtoToEntity(dto, entity);
			
			entity = repository.save(entity);
			
			return new ReviewFullDTO(entity);
		}catch(DataIntegrityViolationException ex) {
			throw new DatabaseException("Constraint violation.");
		}
	}
	
	@Transactional
	public ReviewFullDTO update(Long id, ReviewFullDTO dto) {
		
		try {
			Review entity = repository.getById(id);
			
			copyDtoToEntity(dto, entity);
			
			entity = repository.save(entity);
			
			return new ReviewFullDTO(entity);
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
