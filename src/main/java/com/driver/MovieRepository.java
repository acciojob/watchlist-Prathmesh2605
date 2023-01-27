package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    private HashMap<String, Movie> movieDb;
    private HashMap<String, Director> directorDb;
    private HashMap<String, List<String>> movieDirectorDb;

    public MovieRepository(){
        this.movieDb = new HashMap<String, Movie>();
        this.directorDb = new HashMap<String, Director>();
        this.movieDirectorDb = new HashMap<String, List<String>>();
    }

    //add movie
    public String addMovie(Movie movie){
        movieDb.put(movie.getName(),movie);
        return "Movie Added Successfully";
    }
    //Add director
    public String addDirector(Director director){
        directorDb.put(director.getName(),director);
        return "Director has been added";
    }
    //Movie-Director pair
    public String addMovieDirector(String movie, String director){
        if(movieDb.containsKey(movie) && directorDb.containsKey(director)){
            movieDb.put(movie, movieDb.get(movie));
            directorDb.put(director, directorDb.get(director));
            List<String> allMovies =  new ArrayList<>();
            if(movieDirectorDb.containsKey(director)){
                allMovies = movieDirectorDb.get(director);
            }
            allMovies.add(movie);
            movieDirectorDb.put(director,allMovies);
        }
        return "Added Successfully";
    }
    //Get movie by name
    public Movie getMovie(String name){
        if(movieDb.containsKey(name)){
        return movieDb.get(name);
        }
        return null;
    }

    //Get director by name
    public Director getDirector(String name){
        if(directorDb.containsKey(name)){
            return directorDb.get(name);
        }
        return null;

    }
    //get all movies
    public List getAllMovies(){
        List<String > ls = new ArrayList<>();
        for(String Key: movieDb.keySet()){
            ls.add(Key);
            }
        return ls;
    }

    //Get all movies by director
    public List<String> getMoviesByDirector(String name){
        List<String> result = new ArrayList<String>();
        if(movieDirectorDb.containsKey(name)){
            result = movieDirectorDb.get(name);
        }
        return result;
    }
    //Delete director and it's movies
    public String deleteDirectorByName(String name){
        List<String> movies = new ArrayList<String>();
        if(movieDirectorDb.containsKey(name)){
            movies = movieDirectorDb.get(name);
            for(String movie: movies){
                if(movieDb.containsKey(movie)){
                    movieDb.remove(movie);
                }
            }

            movieDirectorDb.remove(name);
        }

        if(directorDb.containsKey(name)){
            directorDb.remove(name);
        }
        return "Successfully removed";

    }
    //Delete all director
    public String deleteAllDirectors(){
        HashSet<String> movies = new HashSet<>();
        for(String directors: movieDirectorDb.keySet()){
            movies.addAll(movieDirectorDb.get(directors));
        }
        for(String movie: movies){
            movieDb.remove(movie);
        }
        directorDb.clear();
        movieDirectorDb.clear();

        return "Successfully deleted";

    }





}
