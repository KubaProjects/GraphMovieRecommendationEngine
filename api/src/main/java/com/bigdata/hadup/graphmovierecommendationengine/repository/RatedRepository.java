package com.bigdata.hadup.graphmovierecommendationengine.repository;

import com.bigdata.hadup.graphmovierecommendationengine.model.Rated;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RatedRepository extends Neo4jRepository<Rated, Long> {
}
