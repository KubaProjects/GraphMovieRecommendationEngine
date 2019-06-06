package com.bigdata.hadup.graphmovierecommendationengine.repository;

import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.model.RelationWithFreq;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {

    @Query(value = "MATCH (p:Person {name:'Quentin Tarantino'})-[r1:DIRECTED]->(m:Movie), " +
            "(m)<-[r2:ACTED_IN]-(p2:Person), " +
            "(m)-[r3:BELONGS_TO]->(g:Genre) RETURN *")
    Set<Movie> getAllTarantinoMovies();

    @Query(value = "MATCH(m:Movie) WHERE id(m)={movieId} RETURN *")
        //"MATCH (p:Person)-[:ACTED_IN]->(m:Movie) WHERE ID(m)={movieId} RETURN *"
    Set<Movie> getRandomMoviesList(@Param("movieId") Long movieId);

    @Query(value = "MATCH (m:Movie)-[:BELONGS_TO]->(g:Genre) " +
            "WHERE (g.name)={genreName} AND (m.numVotes)>10000 AND (m.rating)>7 " +
            "RETURN * LIMIT 100")
        //"MATCH (p:Person)-[:ACTED_IN]->(m:Movie) WHERE ID(m)={movieId} RETURN *"
    Set<Movie> getMoviesListByGenreAndRtings(@Param("genreName") String genreName);

    @Query(value = "MATCH (m:Movie)-[:BELONGS_TO]->(g:Genre) " +
            "WHERE (g.name)={genreName} AND (m.numVotes)>15000 AND (m.rating)>8 " +
            "RETURN * LIMIT 100")
        //"MATCH (p:Person)-[:ACTED_IN]->(m:Movie) WHERE ID(m)={movieId} RETURN *"
    Set<Movie> getRecomendedMovies(@Param("genreName") String genreName);

    @Query(value = "MATCH (u:Person {name: {0}})-[r:RATED]->(m:Movie) WITH u, avg(r.rate) AS mean MATCH (u)-[r:RATED]->(m:Movie)-[:BELONGS_TO]->(g:Genre) WHERE r.rate > mean WITH u, g, COUNT(*) AS score MATCH (g)<-[:BELONGS_TO]-(rec:Movie) WHERE NOT exists((u)-[:RATED]->(rec)) RETURN rec ORDER BY score DESC LIMIT 20")
    List<Movie> recommendationByCommonGenres(String userLogin);

    @Query(value = "MATCH (u1:Person {name:{0}})-[r:RATED]->(m:Movie) WITH u1, avg(r.rate) AS u1_mean MATCH (u1)-[r1:RATED]->(m:Movie)<-[r2:RATED]-(u2) WITH u1, u1_mean, u2, COLLECT({r1: r1, r2: r2}) AS ratings WHERE size(ratings) > 10 MATCH (u2)-[r:RATED]->(m:Movie) WITH u1, u1_mean, u2, avg(r.rate) AS u2_mean, ratings UNWIND ratings AS r WITH sum( (r.r1.rate-u1_mean) * (r.r2.rate-u2_mean) ) AS nom, sqrt( sum( (r.r1.rate - u1_mean)^2) * sum( (r.r2.rate - u2_mean) ^2)) AS denom, u1, u2 WHERE denom <> 0 WITH u1, u2, nom/denom AS pearson ORDER BY pearson DESC LIMIT 10 MATCH (u2)-[r:RATED]->(m:Movie) WHERE NOT EXISTS( (u1)-[:RATED]->(m) ) RETURN m, SUM( pearson * r.rate) AS score ORDER BY score DESC LIMIT 25\n")
    List<Movie> pearsonKnnRecommendation(String userLogin);

    @Query("MATCH (m:Movie) WHERE m.rating > {rating} AND m.numVotes > 10000 RETURN m, rand() as r ORDER BY r LIMIT 1")
    Movie getRandomMovieWithRatingAbove(@Param("rating") Double rating);

    @Query("MATCH (m: Movie), (x: Movie) WHERE id(x)={movieId} AND NOT (m)-[:GENRE]-(x) AND m.rating > {rating} AND m.numVotes > 10000 RETURN m, rand() as r ORDER BY r LIMIT 1")
    Movie getRandomMovieOfDifferentGenreAndRatingAbove(@Param("movieId") Long movieId, @Param("rating") Double rating);

    @Query("MATCH (m:Movie), (x: Movie) WHERE id(x) = {movieId} AND NOT EXISTS ((m)--(x)) AND m.rating > {rating} AND m.numVotes > 10000 RETURN m, rand() as r ORDER BY r LIMIT 1")
    Movie getRandomUnrelatedMovieWithRatingAbove(@Param("movieId") Long movieId, @Param("rating") Double rating);

    @Query("MATCH (a: Movie)-[r]-(b: Movie) WHERE id(a) = {movieId} AND id(b) IN {movieIds} RETURN count(b) AS freq, type(r) AS type ORDER BY count(b) DESC")
    List<RelationWithFreq> getMostFrequentRelationTypes(@Param("movieId") Long movieId, @Param("movieIds") Set<Long> movieIds);

    @Query("MATCH (m:Movie)-[a:ACTOR]-(l:Movie) WHERE id(m)={movieId} RETURN l ORDER BY a.count DESC LIMIT 25")
    List<Movie> getSameActorsMovies(@Param("movieId") Long movieId);

    @Query("MATCH (m:Movie)-[a:CINEMATOGRAPHER]-(l:Movie) WHERE id(m)={movieId} RETURN l")
    List<Movie> getSameCinematographerMovies(@Param("movieId") Long movieId);

    @Query("MATCH (m:Movie)-[a:COMPOSER]-(l:Movie) WHERE id(m)={movieId} RETURN l")
    List<Movie> getSameComposerMovies(@Param("movieId") Long movieId);

    @Query("MATCH (m:Movie)-[a:DIRECTOR]-(l:Movie) WHERE id(m)={movieId} RETURN l")
    List<Movie> getSameDirectorMovies(@Param("movieId") Long movieId);

    @Query("MATCH (m:Movie)-[a:PRODUCER]-(l:Movie) WHERE id(m)={movieId} RETURN l")
    List<Movie> getSameProducerMovies(@Param("movieId") Long movieId);

    @Query("MATCH (m:Movie)-[a:WRITER]-(l:Movie) WHERE id(m)={movieId} RETURN l")
    List<Movie> getSameWriterMovies(@Param("movieId") Long movieId);

    @Query("MATCH (m:Movie)-[a:GENRE]-(l:Movie) WHERE id(m)={movieId} RETURN l")
    List<Movie> getSameGenreMovies(@Param("movieId") Long movieId);

    @Query("MATCH (a: Movie)-[r]-(b: Movie) WHERE id(a) = {movieId} RETURN b, count(r) ORDER BY count(r) DESC LIMIT 10")
    Set<Movie> getSimilarMovies(@Param("movieId") Long movieId);

    @Query("MATCH (a: Movie)-[r]-(b: Movie) WHERE id(a) IN {movieIds} AND NOT id(b) IN {movieIds} RETURN b, count(r) ORDER BY count(r) DESC LIMIT {limit}")
    Set<Movie> getSimilarMovies(@Param("movieIds") Set<Long> movieIds, @Param("limit") Long limit);

}
