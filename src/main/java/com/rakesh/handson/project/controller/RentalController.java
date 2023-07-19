package com.rakesh.handson.project.controller;

import com.rakesh.handson.project.contract.RentalDto;
import com.rakesh.handson.project.service.RentalService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<RentalDto>> rentedMovieList() {
        return new ResponseEntity<>(rentalService.rentedMovieList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> getRentalMovieById(@PathVariable int id) {
        return new ResponseEntity<>(rentalService.getRentalMovieById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RentalDto> addRentalMovie(@Valid @RequestBody RentalDto rentalDto) {
        RentalDto rentalResponse = rentalService.addRentalMovie(rentalDto);
        return new ResponseEntity<>(rentalResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDto> updateRentalMovieById(@PathVariable int id, @Valid@RequestBody RentalDto rental) {
        RentalDto updatedRentalMovie = rentalService.updateRentalMovieById(id, rental);
        return new ResponseEntity<>(updatedRentalMovie, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRentedMovieById(@PathVariable int id) {
        rentalService.deleteRentedMovieById(id);
        return ResponseEntity.ok("Movie with ID " + id + " has been deleted.");
    }


}
