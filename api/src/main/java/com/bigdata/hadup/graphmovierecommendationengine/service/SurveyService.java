package com.bigdata.hadup.graphmovierecommendationengine.service;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import java.util.Set;

/**
 *
 * @author mateusz
 */
public interface SurveyService {

    Set<Movie> getRandomMoviesWithUniqueGenre(int size);

}
