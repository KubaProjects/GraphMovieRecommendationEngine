package com.bigdata.hadup.graphmovierecommendationengine.controller;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/tarantino")
    public ResponseEntity<Set<Movie>> getAllTarantinoMovies() {
        return new ResponseEntity<>(movieService.getTarantinoMovies(), HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Set<Movie>> getRandomMovie(@PathVariable("id") Long id) {
        return new ResponseEntity<>(movieService.getRandomMoviesList(id), HttpStatus.OK);
    }

    @GetMapping(value = "bygenre/{genreName}")
    public ResponseEntity<Set<Movie>> getMoviesListByGenreAndRtings(@PathVariable("genreName") String genreName) {
        return new ResponseEntity<>(movieService.getMoviesListByGenreAndRtings(genreName), HttpStatus.OK);
    }

    @GetMapping(value = "recomendation/{genreName}")
    public ResponseEntity<Set<Movie>> getRecomendedMovies(@PathVariable("genreName") String genreName) {
        return new ResponseEntity<>(movieService.getMoviesListByGenreAndRtings(genreName), HttpStatus.OK);
    }

}
