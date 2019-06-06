package com.bigdata.hadup.graphmovierecommendationengine.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.io.Serializable;
import java.util.Objects;

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
    private Long numVotes;

    @Property("rating")
    private Double rating;

    @Property("year")
    private Integer year;

    @Property("cinematographers")
    private String cinematographers;

    @Property("actors")
    private String actors;

    @Property("composers")
    private String composers;

    @Property("directors")
    private String directors;

    @Property("writers")
    private String writers;

    @Property("producers")
    private String producers;

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

    public Long getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(Long numVotes) {
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

    public String getCinematographers() {
        return cinematographers;
    }

    public void setCinematographers(String cinematographers) {
        this.cinematographers = cinematographers;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getComposers() {
        return composers;
    }

    public void setComposers(String composers) {
        this.composers = composers;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
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
                Objects.equals(cinematographers, movie.cinematographers) &&
                Objects.equals(actors, movie.actors) &&
                Objects.equals(composers, movie.composers) &&
                Objects.equals(directors, movie.directors) &&
                Objects.equals(writers, movie.writers) &&
                Objects.equals(producers, movie.producers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, additionalId, title, length, numVotes, rating, year, cinematographers, actors, composers, directors, writers, producers);
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
                ", cinematographers='" + cinematographers + '\'' +
                ", actors='" + actors + '\'' +
                ", composers='" + composers + '\'' +
                ", directors='" + directors + '\'' +
                ", writers='" + writers + '\'' +
                ", producers='" + producers + '\'' +
                '}';
    }
}