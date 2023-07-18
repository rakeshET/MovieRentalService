package com.rakesh.handson.project.contract;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto {
    private int id;
    private int movieId;
    private String userId;
    private LocalDate rentalDate;
    private LocalDate returnDate;

}
