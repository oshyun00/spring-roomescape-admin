package roomescape.domain;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class Reservations {
    private JdbcTemplate jdbcTemplate;

    public Reservations(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Reservation> findAll() {
        return jdbcTemplate.query("select * from reservation", reservationRowMapper());
    }

    public void create(Reservation reservation) {
        jdbcTemplate.update("INSERT INTO reservation (name, date, time) VALUES (?, ?, ?)",
                reservation.getName(), reservation.getDate(), reservation.getTime());
    }

    public Reservation findById(long id) {
        return jdbcTemplate.queryForObject("SELECT * from reservation WHERE id = ?", reservationRowMapper(), id);
    }

    private RowMapper<Reservation> reservationRowMapper() {
        return (resultSet, rowNum) -> {
            Reservation reservation = new Reservation(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("date"),
                    resultSet.getString("time"));

            return reservation;
        };
    }

    public void remove(Reservation findReservation) {
        jdbcTemplate.update("DELETE FROM reservation WHERE id = ?", findReservation.getId());
    }
}
