package com.bigdata.hadup.graphmovierecommendationengine.service;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface MovieService {

    Set<Movie> getTarantinoMovies();

    List<Movie> getAllMovies(Pageable pageable);

    long getAllMoviesCount(Pageable pageable);

    Movie getMovieById(long id);
}
