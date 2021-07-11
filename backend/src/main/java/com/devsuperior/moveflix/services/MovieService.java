package com.devsuperior.moveflix.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.moveflix.dto.MovieDTO;
import com.devsuperior.moveflix.entities.Movie;
import com.devsuperior.moveflix.repositories.MovieRepository;

@Service
public class MovieService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MovieRepository repository;
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findAll(Pageable pageable) {
		
		Pageable sortPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name"));
		
		Page<Movie> page = repository.findAll(sortPageable);
		
		return page.map(x -> new MovieDTO(x));
	}
}
