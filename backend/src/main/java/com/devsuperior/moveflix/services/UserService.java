package com.devsuperior.moveflix.services;

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

import com.devsuperior.moveflix.dto.RoleDTO;
import com.devsuperior.moveflix.dto.UserDTO;
import com.devsuperior.moveflix.dto.UserFullDTO;
import com.devsuperior.moveflix.entities.Role;
import com.devsuperior.moveflix.entities.User;
import com.devsuperior.moveflix.repositories.RoleRepository;
import com.devsuperior.moveflix.repositories.UserRepository;
import com.devsuperior.moveflix.services.exceptions.DatabaseException;
import com.devsuperior.moveflix.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private void copyDtoToEntity(UserFullDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		
		entity.getRoles().clear();
		
		for(RoleDTO roleDto : dto.getRoles()) {
			
			Role role = roleRepository.getById(roleDto.getId());
			
			entity.getRoles().add(role);
		}
	}
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable) {
		
		Page<User> page = repository.findAll(pageable);
		
		return page.map(entity -> new UserDTO(entity));
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		
		Optional<User> optional = repository.findById(id);
		
		User entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
		
		return new UserDTO(entity);
	}
	
	@Transactional
	public UserDTO insert(UserFullDTO dto) {
		
		try {
			User entity = new User();
			
			copyDtoToEntity(dto, entity);
			
			entity = repository.save(entity);
			
			return new UserDTO(entity);
		}catch(DataIntegrityViolationException ex) {
			throw new DatabaseException("Constraint violation.");
		}
		
	}
	
	@Transactional
	public UserDTO update(Long id, UserFullDTO dto) {
		
		try {
			User entity = repository.getById(id);
			
			copyDtoToEntity(dto, entity);
			
			entity = repository.save(entity);
			
			return new UserDTO(entity);
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
