package com.progress.sprinthacking.Utils.Reservation;

import com.progress.sprinthacking.DTO.Reservation.ReservationDTO;
import com.progress.sprinthacking.Entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationUtils {
    public ReservationDTO entityToDto(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setPrice(reservation.getPrice());
        dto.setReservationDate(reservation.getReservationDate());

        if (reservation.getGuide() != null) {
            dto.setGuideId(reservation.getGuide().getId());
            dto.setGuideName(reservation.getGuide().getGuideName());
        }

        if (reservation.getTourist() != null) {
            dto.setTouristId(reservation.getTourist().getId());
            dto.setTouristName(reservation.getTourist().getTouristName());
        }
        return dto;
    }
}
