package com.bigdata.hadup.graphmovierecommendationengine.service;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface MovieService {

    Set<Movie> getTarantinoMovies();

    Page<Movie> getMoviesPage(Pageable pageable);

    List<Movie> getMoviesByGenre();

    List<Movie> getMoviesByPearsonKnnRecommendation();

    Movie getMovieById(long id);

    void rateMovie(Long movieId, Integer rate);
}
