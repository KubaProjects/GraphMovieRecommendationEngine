package com.bigdata.hadup.graphmovierecommendationengine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NodeEntity
public class Movie implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Property("id")
    private String additionalId;

    @Property("title")
    private String title;

    @Property("length")
    private Integer length;

    @Property("numVotes")
    private Integer numVotes;

    @Property("rating")
    private Double rating;

    @Property("year")
    private Integer year;

    @Relationship(type = "BELONGS_TO", direction = Relationship.OUTGOING)
    public Set<Genre> genres;

    @Relationship(type = "ACTED_IN", direction = Relationship.INCOMING)
    public Set<Person> actors;

    @Relationship(type = "DIRECTED", direction = Relationship.INCOMING)
    public Set<Person> directors;

    @Relationship(type = "PRODUCED_BY", direction = Relationship.INCOMING)
    public Set<Person> producers;

    @Relationship(type = "WROTE", direction = Relationship.INCOMING)
    public Set<Person> writers;

    @Relationship(type = "COMPOSED_MUSIC_IN", direction = Relationship.INCOMING)
    public Set<Person> musicComposers;

    @Relationship(type = "EDITED_BY", direction = Relationship.INCOMING)
    public Set<Person> editors;


    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdditionalId() {
        return additionalId;
    }

    public void setAdditionalId(String additionalId) {
        this.additionalId = additionalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(Integer numVotes) {
        this.numVotes = numVotes;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Person> getActors() {
        return actors;
    }

    public void setActors(Set<Person> actors) {
        this.actors = actors;
    }

    public Set<Person> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<Person> directors) {
        this.directors = directors;
    }

    public Set<Person> getProducers() {
        return producers;
    }

    public void setProducers(Set<Person> producers) {
        this.producers = producers;
    }

    public Set<Person> getWriters() {
        return writers;
    }

    public void setWriters(Set<Person> writers) {
        this.writers = writers;
    }

    public Set<Person> getMusicComposers() {
        return musicComposers;
    }

    public void setMusicComposers(Set<Person> musicComposers) {
        this.musicComposers = musicComposers;
    }

    public Set<Person> getEditors() {
        return editors;
    }

    public void setEditors(Set<Person> editors) {
        this.editors = editors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) &&
                Objects.equals(additionalId, movie.additionalId) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(length, movie.length) &&
                Objects.equals(numVotes, movie.numVotes) &&
                Objects.equals(rating, movie.rating) &&
                Objects.equals(year, movie.year) &&
                Objects.equals(genres, movie.genres) &&
                Objects.equals(actors, movie.actors) &&
                Objects.equals(directors, movie.directors) &&
                Objects.equals(producers, movie.producers) &&
                Objects.equals(writers, movie.writers) &&
                Objects.equals(musicComposers, movie.musicComposers) &&
                Objects.equals(editors, movie.editors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, additionalId, title, length, numVotes, rating, year, genres, actors, directors, producers, writers, musicComposers, editors);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", additionalId='" + additionalId + '\'' +
                ", title='" + title + '\'' +
                ", length=" + length +
                ", numVotes=" + numVotes +
                ", rating=" + rating +
                ", year=" + year +
                '}';
    }
}