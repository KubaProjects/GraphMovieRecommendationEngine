package com.bigdata.hadup.graphmovierecommendationengine.service;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.model.Person;
import com.bigdata.hadup.graphmovierecommendationengine.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person getTarantino() {
        return personRepository.findByName("Quentin Tarantino");
    }

    @Override
    public Set<Movie> getPersonMoviesList(Long personId) {
        return personRepository.getPersonMoviesList(personId);
    }

    @Override
    public Set<Person> getActorsForMovie(Long movieId) {
        return personRepository.getActorsForMovie(movieId);
    }

    @Override
    public Set<Person> getDirectorsForMovie(Long movieId) {
        return personRepository.getDirectorsForMovie(movieId);
    }

    @Override
    public Set<Movie> getActorRecomendation(String name) {
        return personRepository.getActorRecomendation(name);
    }

    @Override
    public Set<Movie> getDirectorRecomendation(String name) {
        return personRepository.getDirectorRecomendation(name);
    }


}
