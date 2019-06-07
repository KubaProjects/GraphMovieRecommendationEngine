package com.bigdata.hadup.graphmovierecommendationengine.controller;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.model.Person;
import com.bigdata.hadup.graphmovierecommendationengine.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    private PersonService personService;


    @GetMapping(value = "/authenticated")
    @CrossOrigin
    public ResponseEntity<Boolean> loggedIn() {
        if(Objects.nonNull(SecurityContextHolder.getContext().getAuthentication()) &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
            return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication().isAuthenticated(), HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}/movies")
    @CrossOrigin
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
