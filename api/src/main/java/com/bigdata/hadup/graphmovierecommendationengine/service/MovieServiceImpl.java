package com.bigdata.hadup.graphmovierecommendationengine.service;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.model.Person;
import com.bigdata.hadup.graphmovierecommendationengine.model.Rated;
import com.bigdata.hadup.graphmovierecommendationengine.repository.MovieRepository;
import com.bigdata.hadup.graphmovierecommendationengine.repository.PersonRepository;
import com.bigdata.hadup.graphmovierecommendationengine.repository.RatedRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RatedRepository ratedRepository;


    @Override
    public Set<Movie> getTarantinoMovies() {
        return Sets.newHashSet(movieRepository.getAllTarantinoMovies());
    }

    @Override

    public Set<Movie> getRandomMoviesList(Long movieId) { return Sets.newHashSet(movieRepository.getRandomMoviesList(movieId));}

    @Override
    public Set<Movie> getMoviesListByGenreAndRtings(String genreName) { return Sets.newHashSet(movieRepository.getMoviesListByGenreAndRtings(genreName));}

    @Override
    public Set<Movie> getRecomendedMovies(String genreName) { return Sets.newHashSet(movieRepository.getRecomendedMovies(genreName));}


    public Page<Movie> getMoviesPage(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    public Movie getMovieById(long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public void rateMovie(Long movieId, Integer rate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String userName = authentication.getName();
            Person user = personRepository.findByName(userName);
            Movie movie = movieRepository.findById(movieId).get();


            if (Objects.nonNull(movie)) {
                if (Objects.isNull(user)) {
                    user = new Person(userName);
                }
                if (user.getRatedList().stream().filter(m -> m.getMovie().equals(movie)).collect(Collectors.toSet()).size() == 0) {
                    ratedRepository.save(new Rated(rate, user, movie));
                } else {
                    user.getRatedList().stream().filter(m -> m.getMovie().equals(movie)).forEach(m -> m.setRate(rate));
                }
                personRepository.save(user);
            }
        }


    }

}
