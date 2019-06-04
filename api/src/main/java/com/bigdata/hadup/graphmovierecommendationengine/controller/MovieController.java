package com.bigdata.hadup.graphmovierecommendationengine.controller;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
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

    @GetMapping(value = "/by-genre")
    @CrossOrigin
    public ResponseEntity<List<Movie>> getMovieByGenre() {
        return new ResponseEntity<>(movieService.getMoviesByGenre(), HttpStatus.OK);
    }

    @GetMapping(value = "/by-knn")
    @CrossOrigin
    public ResponseEntity<List<Movie>> getMovieByKnn() {
        return new ResponseEntity<>(movieService.getMoviesByPearsonKnnRecommendation(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @CrossOrigin
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/rate/{id}")
    @CrossOrigin
    public void rateMovie(@PathVariable("id") Long id, @RequestBody Integer rate) {
        movieService.rateMovie(id, rate);
    }
}
