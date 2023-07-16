package com.rakesh.handson.project.controller;

import com.rakesh.handson.project.contract.MovieResponse;
import com.rakesh.handson.project.model.Movie;
import com.rakesh.handson.project.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/getAllMovies")
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/getMovieById/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable int id) {
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }

    @PostMapping("/addMovie")
    public ResponseEntity<MovieResponse> addMovie(@RequestBody Movie movie) {
        MovieResponse movieResponse = movieService.addMovie(movie);
        return new ResponseEntity<>(movieResponse, HttpStatus.CREATED);
    }

    @PostMapping("/updateMovieById/{id}")
    public ResponseEntity<MovieResponse> updateMovieById(@PathVariable int id, @RequestBody Movie movie) {
        MovieResponse updatedMovie = movieService.updateMovieById(id, movie);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @DeleteMapping("/deleteMovieById/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable int id) {
        movieService.deleteMovieById(id);
        return ResponseEntity.ok("Movie with ID " + id + " has been deleted.");
    }

}
