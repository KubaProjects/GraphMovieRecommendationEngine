package com.bigdata.hadup.graphmovierecommendationengine.controller;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    private PersonService personService;


    @GetMapping(value = "/{id}/movies")
    @CrossOrigin
    public ResponseEntity<Set<Movie>> getPersonMovieSetById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(personService.getPersonMoviesList(id), HttpStatus.OK);
    }
}
