package com.bigdata.hadup.graphmovierecommendationengine.controller;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/all")
    @CrossOrigin
    public ResponseEntity<Set<Movie>> getAllMovies(Pageable pageable) {
        return new ResponseEntity<>(movieService.getAllMovies(pageable), HttpStatus.OK);
    }


    @GetMapping(value = "/tarantino")
    public ResponseEntity<Set<Movie>> getAllTarantinoMovies() {
        return new ResponseEntity<>(movieService.getTarantinoMovies(), HttpStatus.OK);
    }

}
