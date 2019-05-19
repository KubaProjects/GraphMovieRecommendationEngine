package com.bigdata.hadup.graphmovierecommendationengine.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.io.Serializable;
import java.util.Objects;

@NodeEntity
public class Person implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Property("id")
    private String additionalId;

    @Property("name")
    private String name;

    @Property("birthYear")
    private Integer birthYear;

    @Property("deathYear")
    private Integer deathYear;

    /*@Relationship(type = "ACTED_IN", direction = Relationship.OUTGOING)
    public Set<Movie> actedInMoviesList;

    @Relationship(type = "DIRECTED", direction = Relationship.OUTGOING)
    public Set<Movie> directedMoviesList;

    @Relationship(type = "PRODUCED_BY", direction = Relationship.OUTGOING)
    public Set<Movie> producedMoviesList;

    @Relationship(type = "WROTE", direction = Relationship.OUTGOING)
    public Set<Movie> wroteMoviesList;

    @Relationship(type = "COMPOSED_MUSIC_IN", direction = Relationship.OUTGOING)
    public Set<Movie> composedMusicInMoviesList;

    @Relationship(type = "EDITED_BY", direction = Relationship.OUTGOING)
    public Set<Movie> editedByMoviesList;*/

    public Person() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    /*public Set<Movie> getActedInMoviesList() {
        return actedInMoviesList;
    }

    public void setActedInMoviesList(Set<Movie> actedInMoviesList) {
        this.actedInMoviesList = actedInMoviesList;
    }

    public Set<Movie> getDirectedMoviesList() {
        return directedMoviesList;
    }

    public void setDirectedMoviesList(Set<Movie> directedMoviesList) {
        this.directedMoviesList = directedMoviesList;
    }

    public Set<Movie> getProducedMoviesList() {
        return producedMoviesList;
    }

    public void setProducedMoviesList(Set<Movie> producedMoviesList) {
        this.producedMoviesList = producedMoviesList;
    }

    public Set<Movie> getWroteMoviesList() {
        return wroteMoviesList;
    }

    public void setWroteMoviesList(Set<Movie> wroteMoviesList) {
        this.wroteMoviesList = wroteMoviesList;
    }

    public Set<Movie> getComposedMusicInMoviesList() {
        return composedMusicInMoviesList;
    }

    public void setComposedMusicInMoviesList(Set<Movie> composedMusicInMoviesList) {
        this.composedMusicInMoviesList = composedMusicInMoviesList;
    }

    public Set<Movie> getEditedByMoviesList() {
        return editedByMoviesList;
    }

    public void setEditedByMoviesList(Set<Movie> editedByMoviesList) {
        this.editedByMoviesList = editedByMoviesList;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(additionalId, person.additionalId) &&
                Objects.equals(name, person.name) &&
                Objects.equals(birthYear, person.birthYear) &&
                Objects.equals(deathYear, person.deathYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, additionalId, name, birthYear, deathYear);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", additionalId='" + additionalId + '\'' +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                '}';
    }
}