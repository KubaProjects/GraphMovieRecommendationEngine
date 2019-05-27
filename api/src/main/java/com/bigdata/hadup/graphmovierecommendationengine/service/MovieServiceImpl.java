package com.bigdata.hadup.graphmovierecommendationengine.service;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.repository.MovieRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Set<Movie> getTarantinoMovies() {
        return Sets.newHashSet(movieRepository.getAllTarantinoMovies());
    }

    @Override
    public Set<Movie> getRandomMoviesList(Long movieId) { return Sets.newHashSet(movieRepository.getRandomMoviesList(movieId));}

    @Override
    public Set<Movie> getMoviesListByGenreAndRtings(String genreName) { return Sets.newHashSet(movieRepository.getMoviesListByGenreAndRtings(genreName));}

    @Override
    public Set<Movie> getRecomendedMovies(String genreName) { return Sets.newHashSet(movieRepository.getRecomendedMovies(genreName));}

}
