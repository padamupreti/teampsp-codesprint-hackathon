package com.progress.sprinthacking.Services.Tourist;


import com.progress.sprinthacking.DTO.Reservation.ReservationDTO;
import com.progress.sprinthacking.DTO.Reservation.ReservationRequestDTO;
import com.progress.sprinthacking.Entity.Guide;
import com.progress.sprinthacking.Entity.Reservation;
import com.progress.sprinthacking.Entity.Tourist;
import com.progress.sprinthacking.Entity.User;
import com.progress.sprinthacking.Exception.ResourceNotFoundException;
import com.progress.sprinthacking.Repo.GuideRepo;
import com.progress.sprinthacking.Repo.ReservationRepo;
import com.progress.sprinthacking.Repo.TouristRepo;
import com.progress.sprinthacking.Services.Impl.IReservationService;
import com.progress.sprinthacking.Utils.GetUserFromUserId;
import com.progress.sprinthacking.Utils.Reservation.ReservationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private ReservationRepo reservationRepo;
    @Autowired private GuideRepo guideRepo;
    @Autowired private TouristRepo touristRepo;
    @Autowired private GetUserFromUserId getUserFromUserId;
    @Autowired private ReservationUtils reservationUtils;

    @Override
    @Transactional
    public ReservationDTO createReservation(Long guideId, ReservationRequestDTO requestDTO) {
        // 1. Get the currently logged-in user
        User currentUser = getUserFromUserId.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("No authenticated user found to make a reservation.");
        }

        // 2. Find the Tourist profile associated with the user
        Tourist tourist = touristRepo.findByUser(currentUser)
                .orElseThrow(() -> new IllegalStateException("The current user is not a tourist and cannot reserve a guide."));

        // 3. Find the Guide to be reserved
        Guide guide = guideRepo.findById(guideId)
                .orElseThrow(() -> new ResourceNotFoundException("Guide not found with id: " + guideId));

        // 4. Create and save the new Reservation
        Reservation newReservation = new Reservation();
        newReservation.setTourist(tourist);
        newReservation.setGuide(guide);
        newReservation.setPrice(requestDTO.getPrice());
        newReservation.setReservationDate(ZonedDateTime.now());

        Reservation savedReservation = reservationRepo.save(newReservation);

        return reservationUtils.entityToDto(savedReservation);
    }
}
