package com.bigdata.hadup.graphmovierecommendationengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.FileNotFoundException;
import java.util.*;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Random;
import java.util.ArrayList;

import java.util.Scanner;


@EnableNeo4jRepositories
@SpringBootApplication
public class GraphMovieRecommendationEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphMovieRecommendationEngineApplication.class, args);

        ArrayList<JSONObject> selectedFilms = new ArrayList<>();

        String[] genres = {"Fantasy", "Western", "Sci-Fi", "Biography", "History", "Drama", "War", "Comedy", "Thriller", "Documentary", "Romance", "Crime", "Horror", "Family", "Action", "Animation", "Musical"};
        int[] genreSelectionCounters = new int[genres.length];
        JSONObject [] temporaryPresentedFilmStorage = new JSONObject[5];
        ArrayList<Integer> allGenreIndexes = new ArrayList<>(genres.length);
        for (int i = 0; i < genres.length; i++){
            allGenreIndexes.add(i);
        }

        //ArrayList<Integer> randomGenreIndexes = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        /*
        String s = in.nextLine();
        System.out.println("You entered string "+s);
*/
        Random rand = new Random();
        int rand_int2;

        JSONParser parser = new JSONParser();

        for(int i=0; i< 10; i++){
            List<Integer> randomGenreIndexes = new ArrayList<>(allGenreIndexes);
            Collections.shuffle(randomGenreIndexes);
            randomGenreIndexes = randomGenreIndexes.subList(0,5);
            for(int j=0; j<5; j++) {
                try {
                    URL oracle = new URL("http://localhost:8080/movies/bygenre/" + genres[randomGenreIndexes.get(j)]);
                    URLConnection yc = oracle.openConnection();
                    BufferedReader read = new BufferedReader(new InputStreamReader(yc.getInputStream()));

                    String inputLine;
                    while ((inputLine = read.readLine()) != null) {
                        JSONArray a = (JSONArray) parser.parse(inputLine);

                        rand_int2 = rand.nextInt(a.size());

                        JSONObject movie = (JSONObject) a.get(rand_int2);
                        temporaryPresentedFilmStorage[j]=movie;
                        System.out.println("Title of film num " + j +"("+genres[randomGenreIndexes.get(j)]+") : " + movie.get("title"));
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
            System.out.println("Type index of movie that you like:");
            String choice = in.nextLine();
            selectedFilms.add(temporaryPresentedFilmStorage[Integer.parseInt(choice)]);
            genreSelectionCounters[randomGenreIndexes.get(Integer.parseInt(choice))] +=1;
        }

        int[] findLargestGendreHelper = genreSelectionCounters.clone();
        Arrays.sort(findLargestGendreHelper);
        int mostOccurences = findLargestGendreHelper[findLargestGendreHelper.length - 1];
        ArrayList<Integer> mostOccuredGenres = new ArrayList<>();

        for(int i=0; i<findLargestGendreHelper.length; i++){
            if(genreSelectionCounters[i]==mostOccurences){
                mostOccuredGenres.add(i);
            }
        }
        int idOfSelectedGenreNum1;
        int idOfSelectedGenreNum2;

        if(mostOccuredGenres.size()==1) {
            idOfSelectedGenreNum1=mostOccuredGenres.get(0);
            idOfSelectedGenreNum2=0;
            while(genreSelectionCounters[idOfSelectedGenreNum2] !=findLargestGendreHelper[findLargestGendreHelper.length - 2]){
                idOfSelectedGenreNum2++;
            }

            System.out.println("Occurences of first biggest genre: " + mostOccurences);
            System.out.println("Biggest genre: " + genres[idOfSelectedGenreNum1]);
            System.out.println("Occurences of second biggest genre: " + findLargestGendreHelper[findLargestGendreHelper.length - 2]);
            System.out.println("Second biggest genre: " + genres[idOfSelectedGenreNum2]);
        } else {
            idOfSelectedGenreNum1=mostOccuredGenres.get(0);
            idOfSelectedGenreNum2=mostOccuredGenres.get(1);
            System.out.println("Occurences of first biggest genre: " + mostOccurences);
            System.out.println("Biggest genre: " + genres[idOfSelectedGenreNum1]);
            System.out.println("Occurences of second biggest genre: " + mostOccurences);
            System.out.println("Second biggest genre: " +  genres[idOfSelectedGenreNum2]);
        }
        System.out.println("Recomendations:");
        try {
            URL oracle = new URL("http://localhost:8080/movies/recomendation/" + genres[idOfSelectedGenreNum1]);
            URLConnection yc = oracle.openConnection();
            BufferedReader read = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine;
            while ((inputLine = read.readLine()) != null) {
                JSONArray a = (JSONArray) parser.parse(inputLine);

                rand_int2 = rand.nextInt(a.size());

                JSONObject movie1 = (JSONObject) a.get(0);
                JSONObject movie2 = (JSONObject) a.get(1);
                JSONObject movie3 = (JSONObject) a.get(2);
                System.out.println("Title of film num 1 ("+genres[idOfSelectedGenreNum1]+") : " + movie1.get("title"));
                System.out.println("Title of film num 2 ("+genres[idOfSelectedGenreNum1]+") : " + movie2.get("title"));
                System.out.println("Title of film num 3 ("+genres[idOfSelectedGenreNum1]+") : " + movie3.get("title"));
            }
            read.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            URL oracle = new URL("http://localhost:8080/movies/recomendation/" + genres[idOfSelectedGenreNum2]);
            URLConnection yc = oracle.openConnection();
            BufferedReader read = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine;
            while ((inputLine = read.readLine()) != null) {
                JSONArray a = (JSONArray) parser.parse(inputLine);

                rand_int2 = rand.nextInt(a.size());

                JSONObject movie1 = (JSONObject) a.get(0);
                JSONObject movie2 = (JSONObject) a.get(1);
                System.out.println("Title of film num 4 ("+genres[idOfSelectedGenreNum2]+") : " + movie1.get("title"));
                System.out.println("Title of film num 5 ("+genres[idOfSelectedGenreNum2]+") : " + movie2.get("title"));
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
}


