package roomescape.domain;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class Times {

    private JdbcTemplate jdbcTemplate;

    public Times(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Time> findAll() {
        return jdbcTemplate.query("select * from reservation_time", reservationTimeRowMapper());
    }

    public Time create(Time time) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO reservation_time (start_at) VALUES (?)",
                    new String[]{"id"});
            ps.setString(1, time.getStartAt());
            return ps;
        }, keyHolder);

        long id = keyHolder.getKey().longValue();
        return findById(id);
    }

    public Time findById(long id) {
        return jdbcTemplate.queryForObject("SELECT * from reservation_time WHERE id = ?", reservationTimeRowMapper(),
                id);
    }

    public void remove(Time time) {
        jdbcTemplate.update("DELETE FROM reservation_time WHERE id = ?", time.getId());
    }

    private RowMapper<Time> reservationTimeRowMapper() {
        return (resultSet, rowNum) -> {
            Time time = new Time(
                    resultSet.getLong("id"),
                    resultSet.getString("start_at"));

            return time;
        };
    }
}
