package roomescape.domain;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepository {
    private JdbcTemplate jdbcTemplate;

    public ReservationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Reservation> findAll() {
        return jdbcTemplate.query(
                "SELECT r.id as reservation_id, r.name, r.date, t.id as time_id, t.start_at as time_value FROM reservation as r inner join reservation_time as t on r.time_id = t.id",
                reservationRowMapper());
    }

    public Reservation create(Reservation reservation) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO reservation (name, date, time_id) VALUES (?, ?, ?)",
                    new String[]{"id"});
            ps.setString(1, reservation.getName());
            ps.setString(2, reservation.getDate());
            ps.setLong(3, reservation.getTime().getId());
            return ps;
        }, keyHolder);

        long id = keyHolder.getKey().longValue();
        return findById(id);
    }

    public Reservation findById(long id) {
        return jdbcTemplate.queryForObject(
                "SELECT r.id as reservation_id, r.name, r.date, t.id as time_id, t.start_at as time_value FROM reservation as r inner join reservation_time as t on r.time_id = t.id WHERE r.id = ?",
                reservationRowMapper(), id);
    }

    private RowMapper<Reservation> reservationRowMapper() {
        return (resultSet, rowNum) -> {
            Reservation reservation = new Reservation(
                    resultSet.getLong("reservation_id"),
                    resultSet.getString("name"),
                    resultSet.getString("date"),
                    new ReservationTime(resultSet.getLong("time_id"), resultSet.getString("start_at"))
            );

            return reservation;
        };
    }

    public void remove(Reservation findReservation) {
        jdbcTemplate.update("DELETE FROM reservation WHERE id = ?", findReservation.getId());
    }
}
