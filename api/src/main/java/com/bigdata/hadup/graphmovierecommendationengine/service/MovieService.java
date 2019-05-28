package com.bigdata.hadup.graphmovierecommendationengine.service;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface MovieService {

    Set<Movie> getTarantinoMovies();

    Set<Movie>  getAllMovies(Pageable pageable);
}
