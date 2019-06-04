package com.bigdata.hadup.graphmovierecommendationengine.controller;

import com.bigdata.hadup.graphmovierecommendationengine.algorithm.DataProcessing;
import com.bigdata.hadup.graphmovierecommendationengine.algorithm.RecomendationAlgorithm;
import com.bigdata.hadup.graphmovierecommendationengine.dto.MovieIdsDTO;
import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.service.MovieService;
import com.bigdata.hadup.graphmovierecommendationengine.service.SurveyService;
import java.util.HashSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<Set<Movie>> generateSurvey() {
        return new ResponseEntity<>(surveyService.getRandomMoviesWithUniqueGenre(5), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Set<Movie>> getResults(@RequestBody MovieIdsDTO movieIdsDTO) {
        DataProcessing dp = new DataProcessing();
        Set<Movie> movies = new HashSet<>();
        for (long movieId : movieIdsDTO.getMovieIds()) {
            Movie movie = movieService.getMovieById(movieId);
            if (movie != null) {
                movies.add(movie);
            }
        }
        dp.setSelectedMovies(movies);

        dp.processQuestionnaireData();

        RecomendationAlgorithm algo = new RecomendationAlgorithm(dp);
        algo.recomendation();

        return new ResponseEntity<>(new HashSet<>(), HttpStatus.OK);
    }

}
