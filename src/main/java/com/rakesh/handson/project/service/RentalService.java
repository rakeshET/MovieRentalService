package com.rakesh.handson.project.service;

import com.rakesh.handson.project.contract.MovieResponse;
import com.rakesh.handson.project.contract.RentalResponse;
import com.rakesh.handson.project.exception.MovieNotFoundException;
import com.rakesh.handson.project.exception.RentedMovieNotFoundException;
import com.rakesh.handson.project.model.Movie;
import com.rakesh.handson.project.model.Rental;
import com.rakesh.handson.project.repository.MovieRepository;
import com.rakesh.handson.project.repository.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RentalService {
    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }


    public List<RentalResponse> RentedMovieList() {
        List<Rental> rentals = this.rentalRepository.findAll();
        List<RentalResponse> responses = new ArrayList<>();
        for (Rental rented : rentals) {
            responses.add(
                    RentalResponse
                            .builder()
                            .id(rented.getId())
                            .movieId(rented.getMovieId())
                            .userId(rented.getUserId())
                            .rentalDate(rented.getRentalDate())
                            .returnDate(rented.getReturnDate())
                            .build()
            );
        }
        return responses;
    }


    public RentalResponse getRentalMovieById(int id) {
        Rental rental = this.rentalRepository.findById(id).orElseThrow(() -> {
            log.error("Movie with id: {} not found", id);
            return new RentedMovieNotFoundException(id);
        });
        return RentalResponse
                .builder()
                .id(rental.getId())
                .movieId(rental.getMovieId())
                .userId(rental.getUserId())
                .rentalDate(rental.getRentalDate())
                .returnDate(rental.getReturnDate())
                .build();
    }


    public RentalResponse addRentalMovie(Rental rental) {
        rental.setMovieId(rental.getMovieId());
        rental.setUserId(rental.getUserId());
        rental.setRentalDate(rental.getRentalDate());
        rental.setReturnDate(rental.getReturnDate());

        Rental savedRentalMovie = rentalRepository.save(rental);

        return RentalResponse.builder()
                .id(savedRentalMovie.getId())
                .movieId(savedRentalMovie.getMovieId())
                .userId(savedRentalMovie.getUserId())
                .rentalDate(savedRentalMovie.getRentalDate())
                .returnDate(savedRentalMovie.getReturnDate())
                .build();
    }
    public RentalResponse updateMovieById(int id, Rental rental) {
        Rental nonRentedMovie = rentalRepository.findById(id).orElseThrow(() -> {
            log.error("Movie with id: {} not found", id);
            return new RentedMovieNotFoundException(id);
        });

        nonRentedMovie.setMovieId(rental.getMovieId());
        nonRentedMovie.setUserId(rental.getUserId());
        nonRentedMovie.setRentalDate(rental.getRentalDate());
        nonRentedMovie.setReturnDate(rental.getReturnDate());

        Rental updatedRentalMovie = rentalRepository.save(nonRentedMovie);

        return RentalResponse.builder()
                .id(updatedRentalMovie.getId())
                .movieId(updatedRentalMovie.getMovieId())
                .userId(updatedRentalMovie.getUserId())
                .rentalDate(updatedRentalMovie.getRentalDate())
                .returnDate(updatedRentalMovie.getReturnDate())
                .build();
    }
    public void deleteRentedMovieById(int id) {
        if (!rentalRepository.existsById(id)) {
            throw new MovieNotFoundException(id);
        }
        rentalRepository.deleteById(id);
    }
}
