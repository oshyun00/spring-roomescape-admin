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
    private AtomicLong index = new AtomicLong(1);

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
                index.getAndIncrement(), createDto.getName(), createDto.getDate(), createDto.getTime()
        );
        reservations.create(reservation);
        ReservationResponseDto responseDto = ReservationResponseDto.from(reservation);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    private String redirect() {
        return "redirect:/admin/reservation";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
        Reservation findReservation = reservations.findById(id);

        reservations.remove(findReservation);
        return ResponseEntity.noContent().build();
    }
}
