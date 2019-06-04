package com.bigdata.hadup.graphmovierecommendationengine.algorithm;

import com.bigdata.hadup.graphmovierecommendationengine.model.Genre;
import com.bigdata.hadup.graphmovierecommendationengine.model.Movie;
import com.bigdata.hadup.graphmovierecommendationengine.model.Person;
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
import java.util.*;

import java.util.Random;
import java.util.ArrayList;

import java.util.Scanner;

public class DataProcessing {

    String[] genres = {"Fantasy", "Western", "Sci-Fi", "Biography", "History", "Drama", "War", "Comedy", "Thriller", "Documentary", "Romance", "Crime", "Horror", "Family", "Action", "Animation", "Musical"};
    int[] genreSelectionCounters = new int[genres.length];
    JSONObject [] temporaryPresentedFilmStorage = new JSONObject[5];
    List<String>[]temporaryArrayOfActorsList = new List[5];
    List<String>[]temporaryArrayOfDirectorsList = new List[5];
    int idOfSelectedGenreNum1;
    int idOfSelectedGenreNum2;
    String nameOfMostOccuredDirector;
    String nameOfMostOccuredActor;
    int numOfOccurencecOfBestDirector;
    int numOfOccurencecOfBestActor;

    Map< String,Integer> actorsMap = new HashMap< String,Integer>();
    Map< String,Integer> directorsMap = new HashMap< String,Integer>();

    JSONParser parser = new JSONParser();
    Random rand = new Random();
    int rand_int2;

    public void setSelectedMovies(Set<Movie> movies) {
        List<String> genresList = Arrays.asList(this.genres);
        for (Movie movie : movies) {
            List<Genre> genres = new ArrayList(movie.getGenres());
            for (Genre genre : genres) {
                if (genresList.contains(genre.getName())) {
                    genreSelectionCounters[genresList.indexOf(genre.getName())]++;
                }
            }

            List<Person> actors = new ArrayList(movie.getActors());
            for (Person actor : actors) {
                int count = actorsMap.containsKey(actor.getName()) ? actorsMap.get(actor.getName()) : 0;
                actorsMap.put(actor.getName(), count + 1);
            }

            List<Person> directors = new ArrayList(movie.getDirectors());
            for (Person director : directors) {
                int count = directorsMap.containsKey(director.getName()) ? directorsMap.get(director.getName()) : 0;
                directorsMap.put(director.getName(), count + 1);
            }
        }
    }

    public void questionnaire(){

    ArrayList<Integer> allGenreIndexes = new ArrayList<>(genres.length);
        for (int i = 0; i < genres.length; i++){
        allGenreIndexes.add(i);
    }

    Scanner in = new Scanner(System.in);

        for(int i=0; i< 10; i++){
        List<Integer> randomGenreIndexes = new ArrayList<>(allGenreIndexes);
        Collections.shuffle(randomGenreIndexes);
        randomGenreIndexes = randomGenreIndexes.subList(0,5);
        JSONObject movie = new JSONObject();

        for(int j=0; j<5; j++) {
            String allActors = " ACTORS: ";
            String allDirectors = "DIRECTORS: ";
            try {
                URL oracle = new URL("http://localhost:8080/movies/bygenre/" + genres[randomGenreIndexes.get(j)]);
                URLConnection yc = oracle.openConnection();
                BufferedReader read = new BufferedReader(new InputStreamReader(yc.getInputStream()));

                String inputLine;
                while ((inputLine = read.readLine()) != null) {
                    JSONArray a = (JSONArray) parser.parse(inputLine);

                    rand_int2 = rand.nextInt(a.size());

                    movie = (JSONObject) a.get(rand_int2);
                    temporaryPresentedFilmStorage[j]=movie;
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
                URL oracle = new URL("http://localhost:8080/persons/actorsformovies/" + movie.get("id"));
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
                        allActors += actorName + ", ";
                    }
                    temporaryArrayOfActorsList[j] = actorsInDisplayedMovie;
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
                URL oracle = new URL("http://localhost:8080/persons/directorsformovies/" + movie.get("id"));
                URLConnection yc = oracle.openConnection();
                BufferedReader read = new BufferedReader(new InputStreamReader(yc.getInputStream()));

                String inputLine;
                while ((inputLine = read.readLine()) != null) {
                    JSONArray a = (JSONArray) parser.parse(inputLine);
                    ArrayList<String> directorsInDisplayedMovie = new ArrayList<>();

                    for (Object o : a) {
                        JSONObject tutorials = (JSONObject) o;

                        String directorName = (String) tutorials.get("name");
                        directorsInDisplayedMovie.add(directorName);
                        allDirectors += directorName + " ";
                    }
                    temporaryArrayOfDirectorsList[j] = directorsInDisplayedMovie;
                }
                read.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            System.out.println("Title of film num " + j + ": " + movie.get("title") + " ("+genres[randomGenreIndexes.get(j)]+"), Year: " +  movie.get("year") + allActors + allDirectors);
        }
        System.out.println("\n");
        System.out.println("Type index of movie that you like:");
        String choice = in.nextLine();
        genreSelectionCounters[randomGenreIndexes.get(Integer.parseInt(choice))] +=1;
        mapActors(Integer.parseInt(choice));
        mapDirectors(Integer.parseInt(choice));
    }
    }

    public void processQuestionnaireData(){

    int[] findLargestGendreHelper = genreSelectionCounters.clone();
        Arrays.sort(findLargestGendreHelper);
    int mostOccurences = findLargestGendreHelper[findLargestGendreHelper.length - 1];
    ArrayList<Integer> mostOccuredGenres = new ArrayList<>();

        for(int i=0; i<findLargestGendreHelper.length; i++){
        if(genreSelectionCounters[i]==mostOccurences){
            mostOccuredGenres.add(i);
        }
    }

        System.out.println("\n");

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
        getBiggestValueInMap("Actors");
        getBiggestValueInMap("Directors");
        System.out.println("\n");
        System.out.println("Occurences of the most selected director: " + numOfOccurencecOfBestDirector);
        System.out.println("Most selected director: " + nameOfMostOccuredDirector);
        System.out.println("Occurences of the most selected actor: " + numOfOccurencecOfBestActor);
        System.out.println("Most selected actor: " +  nameOfMostOccuredActor);

    }

    private void mapActors(int choice){
        for(int i=0; i<temporaryArrayOfActorsList[choice].size(); i++){
            int count = actorsMap.containsKey(temporaryArrayOfActorsList[choice].get(i)) ? actorsMap.get(temporaryArrayOfActorsList[choice].get(i)) : 0;
            actorsMap.put(temporaryArrayOfActorsList[choice].get(i), count + 1);
        }
    }

    private void mapDirectors(int choice){
        for(int j=0; j<temporaryArrayOfDirectorsList[choice].size(); j++){
            int count = directorsMap.containsKey(temporaryArrayOfDirectorsList[choice].get(j)) ? directorsMap.get(temporaryArrayOfDirectorsList[choice].get(j)) : 0;
            directorsMap.put(temporaryArrayOfDirectorsList[choice].get(j), count + 1);
        }
    }

    private void getBiggestValueInMap(String type){
        Map.Entry<String,Integer> maxEntry = null;
        Set< Map.Entry< String,Integer> > set;
        if(type=="Actors"){
            set = actorsMap.entrySet();
        } else {
            set = directorsMap.entrySet();
        }

        for (Map.Entry<String,Integer> entry : set)
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        if(type=="Actors"){
            nameOfMostOccuredActor = maxEntry.getKey();
            numOfOccurencecOfBestActor = maxEntry.getValue();
        } else {
            nameOfMostOccuredDirector = maxEntry.getKey();
            numOfOccurencecOfBestDirector = maxEntry.getValue();
        }
    }

}
