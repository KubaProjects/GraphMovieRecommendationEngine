package com.bigdata.hadup.graphmovierecommendationengine.service;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.model.RelationWithFreq;
import com.bigdata.hadup.graphmovierecommendationengine.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SurveyServiceImpl implements SurveyService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static double AVERAGE_RATING = 6.3;
    private final static int SURVEY_LENGTH = 5;
    private final static int SURVEY_STEPS_COUNT = 10;
    private final static int RECOMMENDED_MOVIES_COUNT = 5;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> generateInitialSurvey() {
        List<Movie> movies = new ArrayList<>();
        Movie movie = movieRepository.getRandomMovieWithRatingAbove(AVERAGE_RATING);
        for (int i = 0; i < SURVEY_LENGTH; i++) {
            movie = movieRepository.getRandomMovieOfDifferentGenreAndRatingAbove(movie.getId(), AVERAGE_RATING);
            movies.add(movie);
        }
        return movies;
    }

    @Override
    public Set<Movie> generateNextSurvey(Long... chosenMoviesIds) {
        Set<Movie> movies = new HashSet<>();
        Movie m = movieRepository.getRandomMovieWithRatingAbove(AVERAGE_RATING);
        logger.info("getRandomMovieWithRatingAbove: " + m.getTitle());
        movies.add(m);

        Movie lastChosenMovie = movieRepository.findById(chosenMoviesIds[chosenMoviesIds.length - 1]).orElse(null);
        if (lastChosenMovie == null) {
            return null;
        }

        logger.info("lastChosenMovie: " + lastChosenMovie.getTitle() + '\n');

        Movie similarMovie = null;
        for (int i = chosenMoviesIds.length - 1; i >= 0 && similarMovie == null; i--) {
            Set<Movie> similarMovies = movieRepository.getSimilarMovies(chosenMoviesIds[i]);
            similarMovie = similarMovies.stream()
                    .filter(movie -> !Arrays.asList(chosenMoviesIds).contains(movie.getId()))
                    .findFirst()
                    .orElse(null);
            if (similarMovie == null) {
                logger.info("Nie udało się znaleźć podobnego filmu, który nie został wybrany w poprzednich ankietach");
            } else {
                logger.info("Udało się znaleźć podobny film do " + chosenMoviesIds[i] + ", który nie został wybrany w poprzednich ankietach: " + similarMovie.getTitle());
            }
        }
        if (similarMovie == null) {
            m = movieRepository.getRandomMovieOfDifferentGenreAndRatingAbove(lastChosenMovie.getId(), AVERAGE_RATING);
            logger.info("getRandomMovieOfDifferentGenre than lastchosen (" + lastChosenMovie.getTitle() + ") AndRatingAbove: " + m.getTitle());
            movies.add(m);
        } else {
            movies.add(similarMovie);
        }

        m = movieRepository.getRandomUnrelatedMovieWithRatingAbove(lastChosenMovie.getId(), AVERAGE_RATING);
        logger.info("getRandomUnrelated to last chosen (" + lastChosenMovie.getTitle() + ") MovieWithRatingAbove: " + m.getTitle());
        movies.add(m);

        if (chosenMoviesIds.length > SURVEY_STEPS_COUNT / 2) {
            Set<Long> chosenMoviesIdsSet = Arrays.stream(chosenMoviesIds)
                    .collect(Collectors.toSet());
            List<RelationWithFreq> relationTypes = movieRepository.getMostFrequentRelationTypes(lastChosenMovie.getId(), chosenMoviesIdsSet);

            if (relationTypes.size() > 0) {
                for (RelationWithFreq relationType : relationTypes) {
                    logger.info("relationType: " + relationType.getType() + ", relationFreq: " + relationType.getFreq());
                }
                RelationWithFreq mostFrequentRelationWithLastMovie = relationTypes.stream()
                        .filter(relationWithFreq -> !Objects.equals(relationWithFreq.getType(), "GENRE"))
                        .max(Comparator.comparing(RelationWithFreq::getFreq))
                        .orElse(relationTypes.get(0));
                logger.info("mostFrequentRelationWithLastMovie: " + mostFrequentRelationWithLastMovie.getType());

                List<Movie> relatedMovies = getRelatedMovies(lastChosenMovie.getId(), mostFrequentRelationWithLastMovie.getType());

                m = relatedMovies.stream()
                        .filter(item -> !Arrays.asList(chosenMoviesIds).contains(item.getId()))
                        .findFirst()
                        .orElse(null);

                if (m != null) {
                    logger.info("getRelatedTo (" + lastChosenMovie.getTitle() + ") Movies by " + mostFrequentRelationWithLastMovie.getType() + ": " + m.getTitle());
                    movies.add(m);
                } else {
                    logger.info("Wszystkie filmy skojarzone z (" + lastChosenMovie.getTitle() + ") by " + mostFrequentRelationWithLastMovie.getType() + " były już wybrane przez użytkownika");
                    m = movieRepository.getRandomUnrelatedMovieWithRatingAbove(lastChosenMovie.getId(), AVERAGE_RATING);
                    logger.info("getRandomUnrelated to last chosen (" + lastChosenMovie.getTitle() + ") MovieWithRatingAbove: " + m.getTitle());
                    movies.add(m);
                }
            } else {
                m = movieRepository.getRandomMovieWithRatingAbove(AVERAGE_RATING);
                logger.info(lastChosenMovie.getTitle() + " nie jest w żaden sposób powiązany z dotychczas wybranymi. Dodaję getRandomMovieWithRatingAbove: " + m.getTitle());
                movies.add(m);
            }
        } else {
            m = movieRepository.getRandomMovieOfDifferentGenreAndRatingAbove(lastChosenMovie.getId(), AVERAGE_RATING);
            logger.info("getRandomMovieOfDifferentGenre than (" + lastChosenMovie.getTitle() + ") AndRatingAbove: " + m.getTitle());
            movies.add(m);
        }

        while (movies.size() < SURVEY_LENGTH) {
            m = movieRepository.getRandomMovieWithRatingAbove(AVERAGE_RATING);
            logger.info("getRandomMovieWithRatingAbove: " + m.getTitle());
            movies.add(m);
        }
        return movies;
    }

    @Override
    public Set<Movie> recommendMovies(Long... chosenMoviesIds) {
        return movieRepository.getSimilarMovies(Arrays.stream(chosenMoviesIds)
                .collect(Collectors.toSet()), (long) RECOMMENDED_MOVIES_COUNT);
    }

    private List<Movie> getRelatedMovies(Long movieId, String relationType) {
        switch (relationType) {
            case "ACTOR":
                return movieRepository.getSameActorsMovies(movieId);
            case "CINEMATOGRAPHER":
                return movieRepository.getSameCinematographerMovies(movieId);
            case "COMPOSER":
                return movieRepository.getSameComposerMovies(movieId);
            case "DIRECTOR":
                return movieRepository.getSameDirectorMovies(movieId);
            case "PRODUCER":
                return movieRepository.getSameProducerMovies(movieId);
            case "WRITER":
                return movieRepository.getSameWriterMovies(movieId);
            default:
                return movieRepository.getSameGenreMovies(movieId);
        }
    }

}
