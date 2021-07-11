package com.devsuperior.moveflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.moveflix.entities.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{

}
