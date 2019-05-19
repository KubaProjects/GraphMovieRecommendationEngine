package com.bigdata.hadup.graphmovierecommendationengine.service;


import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.model.Person;

import java.util.Set;

public interface PersonService {

    Person getTarantino();

    Set<Movie> getPersonMoviesList(Long personId);

    /*Set<Movie> getDirectedMoviesList();

    Set<Movie> getProducedMoviesList();

    Set<Movie> getWroteMoviesList();

    Set<Movie> getComposedMusicInMoviesList();

    Set<Movie> getEditedByMoviesList();*/

}
