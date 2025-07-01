package com.progress.sprinthacking.DTO.Reservation;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ReservationDTO {
    private Long id;
    private Long guideId;
    private String guideName;
    private Long touristId;
    private String touristName;
    private Float price;
    private ZonedDateTime reservationDate;
}
