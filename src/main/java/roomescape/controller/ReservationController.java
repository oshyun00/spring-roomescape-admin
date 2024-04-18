package roomescape.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import roomescape.domain.Reservation;
import roomescape.domain.Reservations;
import roomescape.dto.ReservationCreateDto;
import roomescape.dto.ReservationResponseDto;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private Reservations reservations;

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> readReservations() {
        List<Reservation> findReservations = reservations.findAll();
        List<ReservationResponseDto> responseDtos = new ArrayList<>();
        findReservations.forEach(reservation -> responseDtos.add(ReservationResponseDto.from(reservation)));
        return ResponseEntity.ok(responseDtos);
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestBody ReservationCreateDto createDto) {
        Reservation reservation = new Reservation(
                0, createDto.getName(), createDto.getDate(), createDto.getTime()
        );
        Reservation createdReservation = reservations.create(reservation);
        ReservationResponseDto responseDto = ReservationResponseDto.from(createdReservation);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
        Reservation findReservation = reservations.findById(id);

        reservations.remove(findReservation);
        return ResponseEntity.noContent().build();
    }
}
