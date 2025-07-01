package com.progress.sprinthacking.Controller.Tourist;

import com.progress.sprinthacking.DTO.Reservation.ReservationDTO;
import com.progress.sprinthacking.DTO.Reservation.ReservationRequestDTO;
import com.progress.sprinthacking.DTO.ResponseDTO;
import com.progress.sprinthacking.DTO.tourist.TouristDTO;
import com.progress.sprinthacking.DTO.tourist.TouristRequestDTO;
import com.progress.sprinthacking.Services.Impl.IReservationService;
import com.progress.sprinthacking.Services.Impl.ITouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tourist")
public class TouristController {
    @Autowired
    private ITouristService touristService;

    @Autowired
    private IReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createTourist(@RequestBody TouristRequestDTO touristRequestDTO) {
        TouristDTO createdTourist = touristService.createTourist(touristRequestDTO);
        Map<String, Object> response = Map.of("tourist", createdTourist);
        return new ResponseEntity<>(ResponseDTO.success("Tourist created successfully", response), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllTourists() {
        List<TouristDTO> tourists = touristService.getAllTourists();
        Map<String, Object> response = Map.of("tourists", tourists);
        return new ResponseEntity<>(ResponseDTO.success("Tourists retrieved successfully", response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getTouristById(@PathVariable Long id) {
        TouristDTO tourist = touristService.getTouristById(id);
        Map<String, Object> response = Map.of("tourist", tourist);
        return new ResponseEntity<>(ResponseDTO.success("Tourist retrieved successfully", response), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateTourist(@PathVariable Long id, @RequestBody TouristDTO touristDTO) {
        TouristDTO updatedTourist = touristService.updateTourist(id, touristDTO);
        Map<String, Object> response = Map.of("tourist", updatedTourist);
        return new ResponseEntity<>(ResponseDTO.success("Tourist updated successfully", response), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteTourist(@PathVariable Long id) {
        touristService.deleteTourist(id);
        return new ResponseEntity<>(ResponseDTO.success("Tourist deleted successfully"), HttpStatus.OK);
    }

    @PostMapping("/reserve/{guideId}")
    @PreAuthorize("hasAuthority('ROLE_TOURIST')")
    public ResponseEntity<ResponseDTO> reserveGuide(
            @PathVariable Long guideId,
            @RequestBody ReservationRequestDTO requestDTO) {

        ReservationDTO createdReservation = reservationService.createReservation(guideId, requestDTO);
        Map<String, Object> response = Map.of("reservation", createdReservation);
        return new ResponseEntity<>(ResponseDTO.success("Guide reserved successfully", response), HttpStatus.CREATED);
    }
}
