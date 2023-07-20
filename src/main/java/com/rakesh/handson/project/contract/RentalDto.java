package com.rakesh.handson.project.contract;

import com.rakesh.handson.project.validation.rentals.ValidMovieId;
import com.rakesh.handson.project.validation.rentals.ValidRentalDate;
import com.rakesh.handson.project.validation.rentals.ValidReturnDate;
import com.rakesh.handson.project.validation.rentals.ValidUserId;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @ValidMovieId private String movieId;

    @ValidUserId private String userId;

    @ValidRentalDate private LocalDate rentalDate;

    @ValidReturnDate private LocalDate returnDate;
}
