package roomescape.service;

import java.sql.Time;
import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.domain.ReservationTime;
import roomescape.domain.TimeRepository;

@Service public class TimeService {
    private final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    public List<ReservationTime> getAllTime(){
        return timeRepository.findAll();
    }

    public ReservationTime createTime(ReservationTime time){
        return timeRepository.create(time);
    }

    public ReservationTime findTimeById(long timeId) {
        return timeRepository.findById(timeId);
    }

    public void deleteTime(ReservationTime time){
        timeRepository.remove(time);
    }
}
