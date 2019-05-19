package com.bigdata.hadup.graphmovierecommendationengine.service;

import com.bigdata.hadup.graphmovierecommendationengine.model.Genre;
import com.bigdata.hadup.graphmovierecommendationengine.repository.GenreRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Set<Genre> getAllGenres() {
        return Sets.newHashSet(genreRepository.findAll());
    }
}
