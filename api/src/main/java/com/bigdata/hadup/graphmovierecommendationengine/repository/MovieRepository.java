package com.bigdata.hadup.graphmovierecommendationengine.repository;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Set;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {

    @Query(value = "MATCH (p:Person {name:'Quentin Tarantino'})-[r1:DIRECTED]->(m:Movie), " +
            "(m)<-[r2:ACTED_IN]-(p2:Person), " +
            "(m)-[r3:BELONGS_TO]->(g:Genre) RETURN *")
    Set<Movie> getAllTarantinoMovies();

    /*@Query(value = "MATCH (g:Genre)<-[]-(m:Movie)<-[]-(p:Person) USING INDEX m:Movie(numVotes) WHERE exists(m.numVotes) RETURN m, p, g ORDER BY m.numVotes",
            countQuery="MATCH (g:Genre)<-[]-(m:Movie)<-[]-(p:Person) USING INDEX m:Movie(numVotes) WHERE exists(m.numVotes) RETURN count(m)")
    Page<Movie> getAllMovies(Pageable pageable);

    @Query(value = "MATCH (g:Genre)<-[]-(m:Movie)<-[]-(p:Person) RETURN m, p, g",
            countQuery="MATCH (g:Genre)<-[]-(m:Movie)<-[]-(p:Person) RETURN count(m)")
    Page<Movie> getAllMovies(Pageable pageable);*/

}
