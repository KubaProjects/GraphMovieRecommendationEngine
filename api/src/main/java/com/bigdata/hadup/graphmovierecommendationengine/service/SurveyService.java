package com.bigdata.hadup.graphmovierecommendationengine.service;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;

import java.util.List;
import java.util.Set;

public interface SurveyService {

    List<Movie> generateInitialSurvey();

    Set<Movie> generateNextSurvey(Long... chosenMoviesIds);

    Set<Movie> recommendMovies(Long... chosenMoviesIds);

}
