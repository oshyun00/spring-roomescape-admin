package roomescape.dto;

import roomescape.domain.Reservation;
import roomescape.domain.ReservationTime;

public class ReservationResponseDto {
    private long id;
    private String name;
    private String date;
    private ReservationTime time;

    private ReservationResponseDto(long id, String name, String date, ReservationTime time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public static ReservationResponseDto from(Reservation reservation) {
        return new ReservationResponseDto(
                reservation.getId(), reservation.getName(), reservation.getDate(), reservation.getTime()
        );
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public ReservationTime getTime() {
        return time;
    }
}
