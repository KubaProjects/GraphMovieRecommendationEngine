package com.bigdata.hadup.graphmovierecommendationengine.repository;

import com.bigdata.hadup.graphmovierecommendationengine.model.Genre;
import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Set;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {

    @Query(value = "MATCH (p:Person {name:'Quentin Tarantino'})-[r1:DIRECTED]->(m:Movie), " +
            "(m)<-[r2:ACTED_IN]-(p2:Person), " +
            "(m)-[r3:BELONGS_TO]->(g:Genre) RETURN *")
    Set<Movie> getAllTarantinoMovies();

    @Query(value = "MATCH (u:Person {name: {0}})-[r:RATED]->(m:Movie) WITH u, avg(r.rate) AS mean MATCH (u)-[r:RATED]->(m:Movie)-[:BELONGS_TO]->(g:Genre) WHERE r.rate > mean WITH u, g, COUNT(*) AS score MATCH (g)<-[:BELONGS_TO]-(rec:Movie) WHERE NOT EXISTS((u)-[:RATED]->(rec)) RETURN rec ORDER BY score DESC LIMIT 20")
    List<Movie> recommendationByCommonGenres(String userLogin);

    @Query(value="MATCH (u1:Person {name:{0}})-[r:RATED]->(m:Movie) WITH u1, avg(r.rate) AS u1_mean MATCH (u1)-[r1:RATED]->(m:Movie)<-[r2:RATED]-(u2) WITH u1, u1_mean, u2, COLLECT({r1: r1, r2: r2}) AS ratings WHERE size(ratings) > 10 MATCH (u2)-[r:RATED]->(m:Movie) WITH u1, u1_mean, u2, avg(r.rate) AS u2_mean, ratings UNWIND ratings AS r WITH sum( (r.r1.rate-u1_mean) * (r.r2.rate-u2_mean) ) AS nom, sqrt( sum( (r.r1.rate - u1_mean)^2) * sum( (r.r2.rate - u2_mean) ^2)) AS denom, u1, u2 WHERE denom <> 0 WITH u1, u2, nom/denom AS pearson ORDER BY pearson DESC LIMIT 10 MATCH (u2)-[r:RATED]->(m:Movie) WHERE NOT EXISTS( (u1)-[:RATED]->(m) ) RETURN m, SUM( pearson * r.rate) AS score ORDER BY score DESC LIMIT 25\n")
    List<Movie> pearsonKnnRecommendation(String userLogin);


}
