package roomescape.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

/*
 * 테스트 데이터베이스 초기 데이터
 * {ID=1, START_AT=10:00}
 * {ID=2, START_AT=11:00}
 */
@JdbcTest
@Sql(scripts = "/reset_test_data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class ReservationTimeRepositoryTest {
    private ReservationTimeRepository reservationTimeRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        reservationTimeRepository = new ReservationTimeRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("모든 예약 시간 데이터를 가져온다.")
    void findAll() {
        // when
        List<ReservationTime> reservationTimes = reservationTimeRepository.findAll();

        // then
        assertThat(reservationTimes).hasSize(2);
    }

    @Test
    @DisplayName("특정 예약 시간 id의 데이터를 조회한다.")
    void findById() {
        // when
        ReservationTime findReservationTime = reservationTimeRepository.findById(2);

        // then
        assertThat(findReservationTime.getStartAt()).isEqualTo("11:00");
    }

    @Test
    @DisplayName("예약 시간을 생성한다.")
    void create() {
        // given
        ReservationTime inputData = new ReservationTime(0L, "13:00");

        // when
        ReservationTime createdTime = reservationTimeRepository.create(inputData);

        // then
        assertAll(
                () -> assertThat(createdTime.getStartAt()).isEqualTo("13:00"),
                () -> assertThat(reservationTimeRepository.findAll()).hasSize(3)
        );
    }
}