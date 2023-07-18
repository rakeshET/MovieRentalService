package com.rakesh.handson.project.controller;

import com.rakesh.handson.project.dto.RentalRequest;
import com.rakesh.handson.project.dto.RentalResponse;
import com.rakesh.handson.project.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RentedMovieList")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }


    @GetMapping
    public ResponseEntity<List<RentalResponse>> rentedMovieList() {
        return new ResponseEntity<>(rentalService.rentedMovieList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponse> getRentalMovieById(@PathVariable int id) {
        return new ResponseEntity<>(rentalService.getRentalMovieById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RentalResponse> addRentalMovie(@RequestBody RentalRequest rental) {
        RentalResponse rentalResponse = rentalService.addRentalMovie(rental);
        return new ResponseEntity<>(rentalResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalResponse> updateRentalMovieById(@PathVariable int id, @RequestBody RentalRequest rental) {
        RentalResponse updatedRentalMovie = rentalService.updateMovieById(id, rental);
        return new ResponseEntity<>(updatedRentalMovie, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRentedMovieById(@PathVariable int id) {
        rentalService.deleteRentedMovieById(id);
        return ResponseEntity.ok("MovieRequest with ID " + id + " has been deleted.");
    }


}
