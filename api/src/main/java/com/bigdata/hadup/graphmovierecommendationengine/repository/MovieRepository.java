package com.bigdata.hadup.graphmovierecommendationengine.repository;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Set;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {

    @Query(value = "MATCH (p:Person {name:\"Quentin Tarantino\"})-[r:DIRECTED]->(m:Movie), " +
            "(m)<-[r2:ACTED_IN]-(p2:Person) RETURN *")
    Set<Movie> getAllTarantinoMovies();

}
