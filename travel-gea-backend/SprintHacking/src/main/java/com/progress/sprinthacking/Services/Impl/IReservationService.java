package com.progress.sprinthacking.Services.Impl;

import com.progress.sprinthacking.DTO.Reservation.ReservationDTO;
import com.progress.sprinthacking.DTO.Reservation.ReservationRequestDTO;

public interface IReservationService {
    ReservationDTO createReservation(Long guideId, ReservationRequestDTO requestDTO);
}
