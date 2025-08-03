package com.reactivespring.client;

import com.reactivespring.domain.MovieInfo;
import com.reactivespring.domain.Review;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
public class MovieReviewsRestClient {

    private WebClient webClient;

    @Value("${restClient.movieReviewsUrl}")
    private String movieReviewsUrl;


    public MovieReviewsRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Review> retrieveMovieReviews(String movieId) {

        Review dummyReview1 = new Review("R1",1L,"movie is ok ok",2.0);
        Review dummyReview2 = new Review("R2",1L,"movie is good",3.0);

        /*var url = movieReviewsUrl.concat("/{id}");
        return webClient
                .get()
                .uri(url, movieId)
                .retrieve()
                .bodyToMono(MovieInfo.class)
                .log(); */
     return Flux.just(dummyReview1,dummyReview2);

    }

}
