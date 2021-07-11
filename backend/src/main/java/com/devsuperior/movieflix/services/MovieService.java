package com.devsuperior.movieflix.services;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.DatabaseException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	private void copyDtoToEntity(MovieDTO dto, Movie entity) {
		entity.setTitle(dto.getTitle());
		entity.setSubTitle(dto.getSubTitle());
		entity.setYear(dto.getYear());
		entity.setImgUrl(dto.getImgUrl());
		entity.setSynopsis(dto.getSynopsis());
		
		entity.setGenre(genreRepository.getById(dto.getGenre().getId()));
	}
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findAll(Pageable pageable) {
		
		Pageable sortPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("title"));
		
		Page<Movie> page = repository.findAll(sortPageable);
		
		return page.map(x -> new MovieDTO(x));
	}
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		
		Optional<Movie> optional = repository.findById(id);
		
		Movie entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
		
		return new MovieDTO(entity);
		
	}
	
	@Transactional
	public MovieDTO insert(MovieDTO dto) {
		
		try {
			Movie entity = new Movie();
			
			copyDtoToEntity(dto, entity);
			
			entity = repository.save(entity);
			
			return new MovieDTO(entity);			
		}catch(DataIntegrityViolationException ex) {
			throw new DatabaseException("Constraint violation.");
		}
	}
	
	@Transactional
	public MovieDTO update(Long id, MovieDTO dto) {
		
		try {
			Movie entity = repository.getById(id);
			
			copyDtoToEntity(dto, entity);
			
			entity = repository.save(entity);
			
			return new MovieDTO(entity);
		}catch(EntityNotFoundException ex) {
			throw new ResourceNotFoundException("Entity not found " + id + ".");
		}catch(DataIntegrityViolationException ex) {
			throw new DatabaseException("Constraint violation.");
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
