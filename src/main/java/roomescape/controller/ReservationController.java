package roomescape.controller;

import java.util.ArrayList;
import java.util.List;
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
import roomescape.domain.ReservationTime;
import roomescape.dto.ReservationCreateDto;
import roomescape.dto.ReservationResponseDto;
import roomescape.service.ReservationService;
import roomescape.service.TimeService;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final TimeService timeService;

    public ReservationController(ReservationService reservationService, TimeService timeService) {
        this.reservationService = reservationService;
        this.timeService = timeService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> readReservations() {
        List<Reservation> findReservations = reservationService.getAllReservations();
        List<ReservationResponseDto> responseDtos = new ArrayList<>();
        findReservations.forEach(reservation -> responseDtos.add(ReservationResponseDto.from(reservation)));
        return ResponseEntity.ok(responseDtos);
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestBody ReservationCreateDto createDto) {
        ReservationTime time = timeService.findTimeById(createDto.getTimeId());
        Reservation reservation = new Reservation(
                0L, createDto.getName(), createDto.getDate(), time);

        Reservation createdReservation = reservationService.createReservation(reservation);
        ReservationResponseDto responseDto = ReservationResponseDto.from(createdReservation);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
