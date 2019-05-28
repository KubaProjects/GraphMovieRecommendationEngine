package com.bigdata.hadup.graphmovierecommendationengine.controller;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.model.Person;
import com.bigdata.hadup.graphmovierecommendationengine.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/tarantino")
    public ResponseEntity<Person> getTarantino() {
        return new ResponseEntity<>(personService.getTarantino(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/movies")
    public ResponseEntity<Set<Movie>> getPersonMovieSetById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(personService.getPersonMoviesList(id), HttpStatus.OK);
    }

    @GetMapping(value = "/actorsformovies/{movieId}")
    public ResponseEntity<Set<Person>> getActorsForMovie(@PathVariable("movieId") Long movieId) {
        return new ResponseEntity<>(personService.getActorsForMovie(movieId), HttpStatus.OK);
    }

    @GetMapping(value = "/directorsformovies/{movieId}")
    public ResponseEntity<Set<Person>> getDirectorsForMovie(@PathVariable("movieId") Long movieId) {
        return new ResponseEntity<>(personService.getDirectorsForMovie(movieId), HttpStatus.OK);
    }

    @GetMapping(value = "/recomendationactors/{name}")
    public ResponseEntity<Set<Movie>> getActorRecomendation(@PathVariable("name") String name) {
        return new ResponseEntity<>(personService.getActorRecomendation(name), HttpStatus.OK);
    }

    @GetMapping(value = "/recomendationdirectors/{name}")
    public ResponseEntity<Set<Movie>> getDirectorRecomendation(@PathVariable("name") String name) {
        return new ResponseEntity<>(personService.getDirectorRecomendation(name), HttpStatus.OK);
    }
}
