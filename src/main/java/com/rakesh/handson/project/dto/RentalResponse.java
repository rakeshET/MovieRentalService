package com.rakesh.handson.project.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalResponse {
    private int id;
    private int movieId;
    private String userId;
    private LocalDate rentalDate;
    private LocalDate returnDate;

}
