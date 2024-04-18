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
import roomescape.domain.Time;
import roomescape.domain.Times;
import roomescape.dto.TimeCreateDto;

@RestController
@RequestMapping("/times")
public class TimeController {
    @Autowired
    private Times times;

    @GetMapping
    public ResponseEntity<List<Time>> readReservations() {
        List<Time> findTimes = times.findAll();
        return ResponseEntity.ok(findTimes);
    }

    @PostMapping
    public ResponseEntity<Time> createTime(@RequestBody TimeCreateDto createDto) {
        Time time = new Time(0, createDto.getStartAt());
        Time createdTime = times.create(time);
        return new ResponseEntity<>(createdTime, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable long id) {
        Time finTime = times.findById(id);

        times.remove(finTime);
        return ResponseEntity.noContent().build();
    }
}
