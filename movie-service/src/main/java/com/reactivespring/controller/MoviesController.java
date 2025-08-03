package com.reactivespring.controller;

import com.reactivespring.client.MovieReviewsRestClient;
import com.reactivespring.client.MoviesInfoRestClient;
import com.reactivespring.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/movies")
public class MoviesController {

    @Autowired
    MoviesInfoRestClient moviesInfoRestClient;

    @Autowired
    MovieReviewsRestClient movieReviewsRestClient;

    @GetMapping("/{id}")
    public Mono<Movie> retrieveMovieById(@PathVariable("id") String movieId) {
        return moviesInfoRestClient.retrieveMovieInfo(movieId)
                .flatMap(movieInfo -> {

                    var movieReviews = movieReviewsRestClient.retrieveMovieReviews(movieId).collectList();

                    return movieReviews.map(reviews -> new Movie(movieInfo, reviews));

                });
    }
}
