package roomescape.console.view;

import java.util.List;
import roomescape.dto.ReservationResponse;
import roomescape.dto.ReservationTimeResponse;

public class OutputView {
    public void printStartMessage() {
        System.out.println("** 방탈출 어드민 프로그램을 시작합니다 **");
    }

    public void printAdminFunction() {
        System.out.println("** 관리자 메뉴 목록 **");
        System.out.println("1. 예약 조회");
        System.out.println("2. 예약 추가");
        System.out.println("3. 예약 삭제");
        System.out.println("4. 예약 시간 조회");
        System.out.println("5. 예약 시간 추가");
        System.out.println("6. 예약 시간 삭제");
        System.out.println("7. 프로그램 종료");
    }

    public void printAllReservations(List<ReservationResponse> allReservations) {
        if (allReservations.size() == 0) {
            System.out.println("존재하는 예약이없습니다.");
        }
        String format = "id: %s, name: %s, date: %s, time-id: %d, start-at: %s";
        for (ReservationResponse response : allReservations) {
            System.out.printf(format,
                    response.id(),
                    response.name(),
                    response.date(),
                    response.time().id(),
                    response.time().startAt()
            );
            System.out.println();
        }
    }

    public void printAllReservationTimes(List<ReservationTimeResponse> allReservationTimes) {
        if (allReservationTimes.size() == 0) {
            System.out.println("존재하는 예약 시간이없습니다.");
        }
        String format = "id: %d, start-at: %s";
        for (ReservationTimeResponse response : allReservationTimes) {
            System.out.printf(format, response.id(), response.startAt());
            System.out.println();
        }
    }

}
