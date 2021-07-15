package com.devsuperior.movieflix.services;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.RoleDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.dto.UserFullDTO;
import com.devsuperior.movieflix.entities.Role;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.RoleRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.DatabaseException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;

@Service
public class UserService implements UserDetailsService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private void copyDtoToEntity(UserFullDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		entity.getRoles().clear();
		
		for(RoleDTO roleDto : dto.getRoles()) {
			
			Role role = roleRepository.getById(roleDto.getId());
			
			entity.getRoles().add(role);
		}
	}
	
	@Transactional(readOnly = true)
	public UserDTO showProfile() {
		
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			
			User entity = new User();
			
			entity = repository.findByEmail(userName);
			
			return new UserDTO(entity);
		}catch(Exception ex) {
			throw new UnauthorizedException("Invalid user");
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findByEmail(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Email not found.");
		}
		
		return user;
	}

}
