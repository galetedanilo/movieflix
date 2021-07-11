package com.devsuperior.moveflix.services;

import java.io.Serializable;
import java.time.Instant;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.moveflix.dto.GenreDTO;
import com.devsuperior.moveflix.entities.Genre;
import com.devsuperior.moveflix.repositories.GenreRepository;
import com.devsuperior.moveflix.services.exceptions.DatabaseException;
import com.devsuperior.moveflix.services.exceptions.ResourceNotFoundException;

@Service
public class GenreService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private GenreRepository repository;
	
	private void copyDtoToEntity(GenreDTO dto, Genre entity) {
		entity.setName(dto.getName());
	}
	
	@Transactional(readOnly = true)
	public Page<GenreDTO> findAll(Pageable pageable) {
		
		Page<Genre> page = repository.findAll(pageable);
		
		return page.map(entity -> new GenreDTO(entity));
	}
	
	@Transactional(readOnly = true)
	public GenreDTO findById(Long id) {
		
		Optional<Genre> optional = repository.findById(id);
		
		Genre entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
		
		return new GenreDTO(entity);
	}
	
	@Transactional
	public GenreDTO insert(GenreDTO dto) {
		try {
			Genre entity = new Genre();
		
			copyDtoToEntity(dto, entity);
		
			entity.setCreatedAt(Instant.now());
		
		
			entity = repository.save(entity);
			
			return new GenreDTO(entity);
		}catch(DataIntegrityViolationException ex) {
			throw new DatabaseException("Constraint violation.");
		}
	}
	
	@Transactional
	public GenreDTO update(Long id, GenreDTO dto) {
		
		try {
			Genre entity = repository.getById(id);
			
			copyDtoToEntity(dto, entity);
			
			entity.setUpdateAt(Instant.now());
			
			entity = repository.save(entity);
			
			return new GenreDTO(entity);
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
