package com.rakesh.handson.project.service;

import com.rakesh.handson.project.model.Movie;
import com.rakesh.handson.project.model.Rental;
import com.rakesh.handson.project.contract.RentalDto;
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

    public List<RentalDto> rentedMovieList() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(rental -> modelMapper.map(rental, RentalDto.class))
                .collect(Collectors.toList());
    }

    public RentalDto getRentalMovieById(int id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> {
            log.error("Rental with id: {} not found", id);
            return new RentedMovieNotFoundException(id);
        });
        return modelMapper.map(rental, RentalDto.class);
    }

    public RentalDto addRentalMovie(RentalDto rentalDto) {
        Rental rentalEntity = modelMapper.map(rentalDto, Rental.class);
        Rental savedRentalMovie = rentalRepository.save(rentalEntity);
        return modelMapper.map(savedRentalMovie, RentalDto.class);
    }

    public RentalDto updateRentalMovieById(int id, RentalDto rentalDto) {
        Rental nonRentedMovie = rentalRepository.findById(id).orElseThrow(() -> {
            log.error("Rental with id: {} not found", id);
            return new RentedMovieNotFoundException(id);
        });

        modelMapper.map(rentalDto, nonRentedMovie);
        Rental updatedRentalMovie = rentalRepository.save(nonRentedMovie);
        return modelMapper.map(updatedRentalMovie, RentalDto.class);
    }

    public void deleteRentedMovieById(int id) {
        if (!rentalRepository.existsById(id)) {
            throw new MovieNotFoundException(id);
        }
        rentalRepository.deleteById(id);
    }


}
