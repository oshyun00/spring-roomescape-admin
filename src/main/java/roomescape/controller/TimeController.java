package roomescape.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.domain.ReservationTime;
import roomescape.domain.TimeRepository;
import roomescape.dto.TimeCreateDto;

@RestController
@RequestMapping("/times")
public class TimeController {
    @Autowired
    private TimeRepository times;

    @GetMapping
    public ResponseEntity<List<ReservationTime>> readReservations() {
        List<ReservationTime> findReservationTimes = times.findAll();
        return ResponseEntity.ok(findReservationTimes);
    }

    @PostMapping
    public ResponseEntity<ReservationTime> createTime(@RequestBody TimeCreateDto createDto) {
        ReservationTime reservationTime = new ReservationTime(0, createDto.getStartAt());
        ReservationTime createdReservationTime = times.create(reservationTime);
        return new ResponseEntity<>(createdReservationTime, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable long id) {
        ReservationTime finReservationTime = times.findById(id);

        times.remove(finReservationTime);
        return ResponseEntity.noContent().build();
    }
}
