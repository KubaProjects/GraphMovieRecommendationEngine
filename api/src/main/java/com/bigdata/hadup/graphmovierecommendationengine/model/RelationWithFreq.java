package com.bigdata.hadup.graphmovierecommendationengine.model;

import org.springframework.data.neo4j.annotation.QueryResult;


@QueryResult
public class RelationWithFreq {
    Long freq;
    String type;

    public Long getFreq() {
        return freq;
    }

    public String getType() {
        return type;
    }
}
