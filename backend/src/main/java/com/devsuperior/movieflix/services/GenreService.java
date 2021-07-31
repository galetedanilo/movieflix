package com.devsuperior.movieflix.services;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;


@Service
public class GenreService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private GenreRepository repository;
			
	@Transactional(readOnly = true)
	public List<GenreDTO> findAll() {
		
		List<Genre> list = repository.findAll(Sort.by("name"));
		
		return list.stream().map(x -> new GenreDTO(x)).collect(Collectors.toList());
	}		

}
