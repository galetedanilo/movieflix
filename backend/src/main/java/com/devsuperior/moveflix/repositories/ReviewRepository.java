package com.devsuperior.moveflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.moveflix.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
