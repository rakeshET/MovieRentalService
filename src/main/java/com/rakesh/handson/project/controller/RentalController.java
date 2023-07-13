package com.rakesh.handson.project.controller;

import com.rakesh.handson.project.Model.Movie;
import com.rakesh.handson.project.Model.Rental;
import com.rakesh.handson.project.Repo.MovieRepo;
import com.rakesh.handson.project.Repo.RentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class RentalController {
    @Autowired
    private RentalRepo rentalRepo;

    @GetMapping("/getAllRentedMovies")
    public ResponseEntity<List<Rental>> getAllRentedMovies() {

        try {
            List<Rental> rentalList = new ArrayList<>();
            rentalRepo.findAll().forEach(rentalList::add);

            if (rentalList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }
            return new ResponseEntity<>(rentalList,HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getRentalMovieById/{id}")
    public ResponseEntity<Rental> getRentalMovieById(@PathVariable int id) {
        Optional<Rental> rentalData = rentalRepo.findById(Math.toIntExact(id));
        if (rentalData.isPresent()) {
            return new ResponseEntity<>(rentalData.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/addRentalMovie")
    public ResponseEntity<Rental> addRental(@RequestBody Rental rental) {
        Rental rentalObj = rentalRepo.save(rental);

        return new ResponseEntity<>(rentalObj, HttpStatus.OK);
    }

    @PostMapping("/updateRentalMovieById/{id}")
    public ResponseEntity<Rental> updateRentalMovieById(@PathVariable int id, @RequestBody Rental newRentalMovieData) {
        Optional<Rental> oldRentalMovieData = rentalRepo.findById(id);
        if (oldRentalMovieData.isPresent()) {
            Rental updatedRentalData = oldRentalMovieData.get();

            updatedRentalData.setId(newRentalMovieData.getId());
            updatedRentalData.setMovieId(newRentalMovieData.getMovieId());
            updatedRentalData.setUserId(newRentalMovieData.getUserId());
            updatedRentalData.setRentalDate(newRentalMovieData.getRentalDate());
            updatedRentalData.setReturnDate(newRentalMovieData.getReturnDate());

            Rental rentalObj = rentalRepo.save(updatedRentalData);
            return new ResponseEntity<>(rentalObj,HttpStatus.OK);

        }
        return new ResponseEntity<>( HttpStatus.OK);

    }

    @DeleteMapping("/deleteRentalMovieById/{id}")
    public ResponseEntity<HttpStatus> deleteRentalMovieById(@PathVariable int id) {
        rentalRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
