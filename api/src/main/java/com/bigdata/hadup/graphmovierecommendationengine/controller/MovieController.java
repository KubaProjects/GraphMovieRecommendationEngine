package com.bigdata.hadup.graphmovierecommendationengine.controller;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


import javax.websocket.server.PathParam;
import java.util.Set;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @GetMapping
    @CrossOrigin
    public ResponseEntity<Page<Movie>> getAllMovies(Pageable pageable) {
        return new ResponseEntity<>(movieService.getMoviesPage(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @CrossOrigin
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
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


    @PostMapping(value = "/rate/{id}")
    @CrossOrigin
    public void rateMovie(@PathVariable("id") Long id, @RequestBody Integer rate) {
        movieService.rateMovie(id, rate);
    }

}
