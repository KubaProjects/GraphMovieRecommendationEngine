package com.bigdata.hadup.graphmovierecommendationengine.model;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "RATED")
public class Rated {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private int rate;

    @StartNode
    private Person user;

    @EndNode
    private Movie movie;


    public Rated() {
    }

    public Rated(int rate, Person user, Movie movie) {
        this.rate = rate;
        this.user = user;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}