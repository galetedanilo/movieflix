package com.devsuperior.movieflix.controllers;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieFullDTO;
import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MovieService service;
	
	
	@GetMapping
	public ResponseEntity<Page<MovieMinDTO>> findAll(Pageable pageable,
			@RequestParam(value = "genreId", defaultValue = "0") Long genreId) {
	
		Page<MovieMinDTO> page = service.findAll(genreId, pageable);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieFullDTO> findById(@PathVariable Long id) {
		
		MovieFullDTO dto = service.findById(id);
		
		return ResponseEntity.ok(dto);
	}
}
