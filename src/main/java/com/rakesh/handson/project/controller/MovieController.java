package com.rakesh.handson.project.controller;

import com.rakesh.handson.project.Model.Movie;
import com.rakesh.handson.project.Repo.MovieRepo;
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
    @Autowired
    private MovieRepo movieRepo;

    @GetMapping("/getAllMovies")
    public ResponseEntity<List<Movie>> getAllMovies() {

        try {
            List<Movie> movieList = new ArrayList<>();
            movieRepo.findAll().forEach(movieList::add);

            if (movieList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }
            return new ResponseEntity<>(movieList,HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getMovieById/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        Optional<Movie> movieData = movieRepo.findById(Math.toIntExact(id));
        if (movieData.isPresent()) {
            return new ResponseEntity<>(movieData.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

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
            return new ResponseEntity<>(movieObj,HttpStatus.OK);

        }
        return new ResponseEntity<>( HttpStatus.OK);

    }

    @DeleteMapping("/deleteMovieById/{id}")
    public ResponseEntity<HttpStatus> deleteMovieById(@PathVariable int id) {
        movieRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
