package com.bigdata.hadup.graphmovierecommendationengine.algorithm;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RecomendationAlgorithm {
    DataProcessing dp;

    JSONParser parser = new JSONParser();

    public RecomendationAlgorithm(DataProcessing dpobject){
        dp = dpobject;
    }

    public void recomendation() {
        System.out.println("\n");
        System.out.println("RECOMENDATIONS:");
        if (dp.numOfOccurencecOfBestDirector == 1 && dp.numOfOccurencecOfBestActor == 1) {
            recomendationForGenres(1,3);
            recomendationForGenres(2,2);
        } else if (dp.numOfOccurencecOfBestDirector > 1 && dp.numOfOccurencecOfBestActor==1) {
            recomendationForGenres(1,2);
            recomendationForGenres(2,2);
            recomendationForActors(dp.nameOfMostOccuredDirector,"Directors");
        } else if (dp.numOfOccurencecOfBestDirector == 1 && dp.numOfOccurencecOfBestActor > 1){
            recomendationForGenres(1,2);
            recomendationForGenres(2,2);
            recomendationForActors(dp.nameOfMostOccuredActor,"Actors");
        } else {
            recomendationForGenres(1,2);
            recomendationForGenres(2,1);
            recomendationForActors(dp.nameOfMostOccuredActor,"Actors");
            recomendationForActors(dp.nameOfMostOccuredDirector,"Directors");
        }
    }

    private void recomendationForGenres(int idOfSelectedGenreNum, int numOfRecomendations){
        int idOfSelected;
        if(idOfSelectedGenreNum == 1){
            idOfSelected = dp.idOfSelectedGenreNum1;
        } else {
            idOfSelected = dp.idOfSelectedGenreNum2;
        }


        try {
            URL oracle = new URL("http://localhost:8080/movies/recomendation/" + dp.genres[idOfSelected]);
            URLConnection yc = oracle.openConnection();
            BufferedReader read = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine;
            while ((inputLine = read.readLine()) != null) {
                JSONArray a = (JSONArray) parser.parse(inputLine);

                List<Integer> randomIndexes = getRandomIndexes(a.size(),numOfRecomendations);
                for(int i=0; i<numOfRecomendations; i++){
                    JSONObject movie1 = (JSONObject) a.get(randomIndexes.get(i));
                    System.out.println("Title of film: "+ movie1.get("title") + " (" + dp.genres[idOfSelected] + ") Year: " +  movie1.get("year")  + getActors((long)movie1.get("id")) + getDirectors((long)movie1.get("id")) );
                }
            }
            read.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void recomendationForActors(String name, String type){
        String url;
        if(type == "Actors"){
            url = "http://localhost:8080/persons/recomendationactors/";
        } else {
            url = "http://localhost:8080/persons/recomendationdirectors/";
        }

        try {
            URL oracle = new URL(url + name);
            URLConnection yc = oracle.openConnection();
            BufferedReader read = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine;
            while ((inputLine = read.readLine()) != null) {
                JSONArray a = (JSONArray) parser.parse(inputLine);

                List<Integer> randomIndexes = getRandomIndexes(a.size(),1);
                for(int i=0; i<1; i++){
                    JSONObject movie1 = (JSONObject) a.get(randomIndexes.get(i));
                    System.out.println("Title of film: "+ movie1.get("title") + " " + " Year: " +  movie1.get("year")  + getActors((long)movie1.get("id")) + getDirectors((long)movie1.get("id")) );
                }
            }
            read.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String getActors(long movieid){
        String actors = " ACTORS: ";

        try {
            URL oracle = new URL("http://localhost:8080/persons/actorsformovies/" + movieid);
            URLConnection yc = oracle.openConnection();
            BufferedReader read = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine;
            while ((inputLine = read.readLine()) != null) {
                JSONArray a = (JSONArray) parser.parse(inputLine);
                ArrayList<String> actorsInDisplayedMovie = new ArrayList<>();

                for (Object o : a) {
                    JSONObject tutorials = (JSONObject) o;

                    String actorName = (String) tutorials.get("name");
                    actorsInDisplayedMovie.add(actorName);
                    actors += actorName + ", ";
                }
            }
            read.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return actors;
    }

    private String getDirectors(long movieid){
        String directors = " DIRECTORS: ";

        try {
            URL oracle = new URL("http://localhost:8080/persons/directorsformovies/" + movieid);
            URLConnection yc = oracle.openConnection();
            BufferedReader read = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine;
            while ((inputLine = read.readLine()) != null) {
                JSONArray a = (JSONArray) parser.parse(inputLine);
                ArrayList<String> actorsInDisplayedMovie = new ArrayList<>();

                for (Object o : a) {
                    JSONObject tutorials = (JSONObject) o;

                    String directorName = (String) tutorials.get("name");
                    actorsInDisplayedMovie.add(directorName);
                    directors += directorName + ", ";
                }
            }
            read.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return directors;
    }

    private List getRandomIndexes(int size, int amount){

        ArrayList<Integer> allGenreIndexes = new ArrayList<>(size);
        for (int i = 0; i < size; i++){
            allGenreIndexes.add(i);
        }

        List<Integer> randomIndexes = new ArrayList<>(allGenreIndexes);
        Collections.shuffle(allGenreIndexes);
        randomIndexes = randomIndexes.subList(0,amount);

        return randomIndexes;
    }
}
