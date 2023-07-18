package com.rakesh.handson.project.service;

import com.rakesh.handson.project.dto.RentalRequest;
import com.rakesh.handson.project.dto.RentalResponse;
import com.rakesh.handson.project.exception.MovieNotFoundException;
import com.rakesh.handson.project.exception.RentedMovieNotFoundException;
import com.rakesh.handson.project.repository.RentalRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RentalService(RentalRepository rentalRepository, ModelMapper modelMapper) {
        this.rentalRepository = rentalRepository;
        this.modelMapper = modelMapper;
    }

    public List<RentalResponse> rentedMovieList() {
        List<RentalRequest> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(rental -> modelMapper.map(rental, RentalResponse.class))
                .collect(Collectors.toList());
    }

    public RentalResponse getRentalMovieById(int id) {
        RentalRequest rental = rentalRepository.findById(id).orElseThrow(() -> {
            log.error("RentalRequest with id: {} not found", id);
            return new RentedMovieNotFoundException(id);
        });
        return modelMapper.map(rental, RentalResponse.class);
    }

    public RentalResponse addRentalMovie(RentalRequest rental) {
        RentalRequest savedRentalMovie = rentalRepository.save(rental);
        return modelMapper.map(savedRentalMovie, RentalResponse.class);
    }

    public RentalResponse updateMovieById(int id, RentalRequest rental) {
        RentalRequest nonRentedMovie = rentalRepository.findById(id).orElseThrow(() -> {
            log.error("RentalRequest with id: {} not found", id);
            return new RentedMovieNotFoundException(id);
        });

        modelMapper.map(rental, nonRentedMovie);
        RentalRequest updatedRentalMovie = rentalRepository.save(nonRentedMovie);
        return modelMapper.map(updatedRentalMovie, RentalResponse.class);
    }

    public void deleteRentedMovieById(int id) {
        if (!rentalRepository.existsById(id)) {
            throw new MovieNotFoundException(id);
        }
        rentalRepository.deleteById(id);
    }


}
