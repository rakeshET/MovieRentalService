package com.rakesh.handson.project.controller;

import com.rakesh.handson.project.contract.RentalResponse;
import com.rakesh.handson.project.model.Rental;
import com.rakesh.handson.project.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/RentedMovieList")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }


    @GetMapping
    public ResponseEntity<List<RentalResponse>> getAllMovies() {
        return new ResponseEntity<>(rentalService.RentedMovieList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponse> getRentalMovieById(@PathVariable int id) {
        return new ResponseEntity<>(rentalService.getRentalMovieById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RentalResponse> addRentalMovie(@RequestBody Rental rental) {
        RentalResponse rentalResponse = rentalService.addRentalMovie(rental);
        return new ResponseEntity<>(rentalResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalResponse> updateRentalMovieById(@PathVariable int id, @RequestBody Rental rental) {
        RentalResponse updatedRentalMovie = rentalService.updateMovieById(id, rental);
        return new ResponseEntity<>(updatedRentalMovie, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRentedMovieById(@PathVariable int id) {
        rentalService.deleteRentedMovieById(id);
        return ResponseEntity.ok("Movie with ID " + id + " has been deleted.");
    }


}
