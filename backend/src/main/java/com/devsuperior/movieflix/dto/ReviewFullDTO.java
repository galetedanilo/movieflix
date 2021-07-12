package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;

public class ReviewFullDTO extends ReviewDTO {

	private static final long serialVersionUID = 1L;

	private MovieReviewDTO movie;
	
	
	public ReviewFullDTO() {
		super();
	}
	
	public ReviewFullDTO(Long id, String text, UserReviewDTO user, MovieReviewDTO movie) {
		super(id, text, user);
		this.movie = movie;
	}

	public ReviewFullDTO(Review entity) {
		super(entity);
		this.movie = new MovieReviewDTO(entity.getMovie());
	}

	public MovieReviewDTO getMovie() {
		return movie;
	}

	public void setMovie(MovieReviewDTO movie) {
		this.movie = movie;
	}

}
