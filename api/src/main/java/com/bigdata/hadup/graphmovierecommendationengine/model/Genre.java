package com.bigdata.hadup.graphmovierecommendationengine.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.io.Serializable;
import java.util.Objects;

@NodeEntity
public class Genre implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Property("id")
    private String additionalId;

    @Property("name")
    private String name;

    public Genre() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id) &&
                Objects.equals(additionalId, genre.additionalId) &&
                Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, additionalId, name);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", additionalId='" + additionalId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
