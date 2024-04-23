package roomescape.controller;

import static org.hamcrest.Matchers.is;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import roomescape.dto.ReservationTimeCreateDto;

/*
 * 테스트 데이터베이스 초기 데이터
 * {ID=1, START_AT=10:00}
 * {ID=2, START_AT=11:00}
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/reset_test_data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class ReservationTimeControllerTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("전체 시간 목록을 조회한다.")
    void readReservationTimes() {
        RestAssured.given().log().all()
                .when().get("/times")
                .then().log().all()
                .statusCode(200)
                .body("size()", is(2));
    }

    @Test
    @DisplayName("예약 시간을 생성한다.")
    void createReservationTime() {
        ReservationTimeCreateDto createDto = new ReservationTimeCreateDto("13:00");

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(createDto)
                .when().post("/times")
                .then().log().all()
                .statusCode(201)
                .header("Location", "/times/3");
    }

    @Test
    @DisplayName("적절하지 않은 형태의 시간을 생성하면 예외가 발생한다.")
    void createIllegalReservationTime() {
        ReservationTimeCreateDto createDto = new ReservationTimeCreateDto("24:01");

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(createDto)
                .when().post("/times")
                .then().log().all()
                .statusCode(400);
    }
}