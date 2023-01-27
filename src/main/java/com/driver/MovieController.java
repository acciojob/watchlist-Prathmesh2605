package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    //Adding movie
    @PostMapping("/movies/add-movie")
    public String addMovie(@RequestBody Movie movie){
        return movieService.addMovie(movie);
    }

    //Adding director
    @PostMapping("/movies/add-director")
    public String addDirector(@RequestBody Director director){
        return movieService.addDirector(director);
    }
    //Adding movie director pair
    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName")String movieName,@RequestParam("directorName") String directorName){
        movieService.addMovieDirector(movieName,directorName);
        return new ResponseEntity<>("Added", HttpStatus.CREATED);
    }

    //Get movie by name
    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String name){
        Movie movie = movieService.getMovie(name);
        if(movie==null)return new ResponseEntity(movie,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(movie, HttpStatus.FOUND);

    }
    //Get director by name
    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable ("name")String name){
        Director director = movieService.getDirector(name);
        return new ResponseEntity<>(director, HttpStatus.FOUND);
    }
    //Get List of movies name for a given director name
    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String  name){
        List<String> result = movieService.getMoviesByDirectorName(name);
        return new ResponseEntity<>(result,HttpStatus.FOUND);
    }
    //get all movies
    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies(){
        List<String> result = movieService.getAllMovies();
        return new ResponseEntity(result,HttpStatus.FOUND);
    }

    //Delete a director and its movies from the records
    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("name") String name){
        String result = movieService.deleteDirector(name);
        return new ResponseEntity(result, HttpStatus.OK);

    }
    //Delete all directors and all movies by them from the records
    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String result = movieService.deleteAllDirectors();
        return new ResponseEntity(result,HttpStatus.OK);
    }
}
