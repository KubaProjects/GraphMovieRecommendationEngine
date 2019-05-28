package com.bigdata.hadup.graphmovierecommendationengine.service;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.repository.MovieRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Set<Movie> getTarantinoMovies() {
        return Sets.newHashSet(movieRepository.getAllTarantinoMovies());
    }

    @Override
    public Set<Movie> getAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable).stream().collect(Collectors.toSet());
    }
}
