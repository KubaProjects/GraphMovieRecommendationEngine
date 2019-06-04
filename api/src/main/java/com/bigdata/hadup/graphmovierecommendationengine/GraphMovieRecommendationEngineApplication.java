package com.bigdata.hadup.graphmovierecommendationengine;

import com.bigdata.hadup.graphmovierecommendationengine.algorithm.DataProcessing;
import com.bigdata.hadup.graphmovierecommendationengine.algorithm.RecomendationAlgorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@EnableNeo4jRepositories
@SpringBootApplication
public class GraphMovieRecommendationEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphMovieRecommendationEngineApplication.class, args);

        DataProcessing dp = new DataProcessing();
        dp.questionnaire();
        dp.processQuestionnaireData();

        RecomendationAlgorithm algo = new RecomendationAlgorithm(dp);
        algo.recomendation();

    }
}


