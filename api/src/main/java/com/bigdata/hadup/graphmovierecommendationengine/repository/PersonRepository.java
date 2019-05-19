package com.bigdata.hadup.graphmovierecommendationengine.repository;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.model.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Person findByName(String name);

    @Query(value = "MATCH (p:Person)-[:ACTED_IN]->(m:Movie) WHERE ID(p)={personId} RETURN *")
    Set<Movie> getPersonMoviesList(@Param("personId") Long personId);

}