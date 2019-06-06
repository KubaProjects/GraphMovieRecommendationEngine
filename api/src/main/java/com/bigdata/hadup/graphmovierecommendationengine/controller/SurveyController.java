package com.bigdata.hadup.graphmovierecommendationengine.controller;

import com.bigdata.hadup.graphmovierecommendationengine.dto.MovieIdsDTO;
import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.service.MovieService;
import com.bigdata.hadup.graphmovierecommendationengine.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getInitialSurvey() {
        return new ResponseEntity(surveyService.generateInitialSurvey(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Set<Movie>> getNextSurvey(@RequestBody MovieIdsDTO movieIdsDTO) {
        return new ResponseEntity(surveyService.generateNextSurvey(movieIdsDTO.getMovieIds()), HttpStatus.OK);
    }

    @PostMapping("/result")
    public ResponseEntity<Set<Movie>> getResults(@RequestBody MovieIdsDTO movieIdsDTO) {
        return new ResponseEntity(surveyService.recommendMovies(movieIdsDTO.getMovieIds()), HttpStatus.OK);
    }

}
