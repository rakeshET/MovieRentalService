package com.rakesh.handson.project.contract;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
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

    @Size(min = 1, message = "Rented movie-id  should be provided")
    private String movieId;

    @Size(min = 1, message = "Rented Person id should be provided")
    private String userId;

    @NotNull(message = "Rental date should be provided")
    @PastOrPresent(message = "Rental date should be in the past or present")
    private LocalDate rentalDate;

    @NotNull(message = "Return date should be provided")
    @FutureOrPresent(message = "Return date should be in the present or future")
    private LocalDate returnDate;
}
