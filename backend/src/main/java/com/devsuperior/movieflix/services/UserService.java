package com.devsuperior.movieflix.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserRepository repository;
	
	private String getAuthenticationUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	@Transactional(readOnly = true)
	public User authenticated() {
		try {
			return repository.findByEmail(getAuthenticationUsername());
		}catch(Exception ex) {
			throw new UnauthorizedUserException("Invalid user.");
		}
	}
	
	@Transactional(readOnly = true)
	public UserDTO getUserProfile() {
		
		return new UserDTO(authenticated());
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
