package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(Movie movie){
        return movieRepository.addMovie(movie);
    }

    public String addDirector(Director director){
        return movieRepository.addDirector(director);
    }

    public String addMovieDirector(String movie, String director){
        return movieRepository.addMovieDirector(movie,director);
    }


    public Movie getMovie(String name){
        return movieRepository.getMovie(name);
    }

    public Director getDirector(String director){
        return movieRepository.getDirector(director);
    }
    public List getMoviesByDirectorName(String director){
        return movieRepository.getMoviesByDirector(director);
    }

    public List getAllMovies(){
        return movieRepository.getAllMovies();
    }

    public String deleteDirector(String name){
        return movieRepository.deleteDirectorByName(name);

    }
    public String deleteAllDirectors(){
        return movieRepository.deleteAllDirectors();
    }

}
