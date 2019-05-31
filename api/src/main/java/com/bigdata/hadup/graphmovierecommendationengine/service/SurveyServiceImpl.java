package com.bigdata.hadup.graphmovierecommendationengine.service;

import com.bigdata.hadup.graphmovierecommendationengine.model.Genre;
import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.repository.GenreRepository;
import com.bigdata.hadup.graphmovierecommendationengine.repository.MovieRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mateusz
 */
@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Set<Movie> getRandomMoviesWithUniqueGenre(int size) {
        Set<Movie> movies = new HashSet(size);
        ArrayList<Genre> genres = (ArrayList<Genre>) genreRepository.findAll();
        Collections.shuffle(genres);
        for (int i = 0; i < size; i++) {
            List<Movie> genreMovies = new ArrayList(movieRepository.getMoviesListByGenreAndRtings(genres.get(i).getName()));
            Collections.shuffle(genreMovies);

            if (genreMovies.isEmpty()) {
                genres.remove(i);
                i--;
            } else {
                movies.add(genreMovies.get(0));
            }
        }
        return movies;
    }

}
