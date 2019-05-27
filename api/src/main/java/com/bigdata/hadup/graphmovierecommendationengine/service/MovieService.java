package com.bigdata.hadup.graphmovierecommendationengine.service;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;

import java.util.Set;

public interface MovieService {

    Set<Movie> getTarantinoMovies();

    Set<Movie> getRandomMoviesList(Long movieId);

    Set<Movie> getMoviesListByGenreAndRtings(String genreName);

    Set<Movie> getRecomendedMovies(String genreName);

}
