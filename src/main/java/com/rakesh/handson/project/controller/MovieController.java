package com.rakesh.handson.project.controller;

import com.rakesh.handson.project.contract.MovieResponse;
import com.rakesh.handson.project.model.Movie;
import com.rakesh.handson.project.repository.MovieRepository;
import com.rakesh.handson.project.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie movieObj = movieRepo.save(movie);

        return new ResponseEntity<>(movieObj, HttpStatus.OK);
    }

    @PostMapping("/updateMovieById/{id}")
    public ResponseEntity<Movie> updateMovieById(@PathVariable int id, @RequestBody Movie newMovieData) {
        Optional<Movie> oldMovieData = movieRepo.findById(id);
        if (oldMovieData.isPresent()) {
            Movie updatedMovieData = oldMovieData.get();

            updatedMovieData.setId(newMovieData.getId());
            updatedMovieData.setTitle(newMovieData.getTitle());
            updatedMovieData.setGenre(newMovieData.getGenre());
            updatedMovieData.setReleaseYear(newMovieData.getReleaseYear());
            updatedMovieData.setStatus(newMovieData.getStatus());

            Movie movieObj = movieRepo.save(updatedMovieData);
            return new ResponseEntity<>(movieObj, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/deleteMovieById/{id}")
    public ResponseEntity<HttpStatus> deleteMovieById(@PathVariable int id) {
        movieRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
