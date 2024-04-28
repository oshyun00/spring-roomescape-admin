package roomescape.console.configuration;

import roomescape.console.domain.ConsoleReservationRepository;
import roomescape.console.domain.ConsoleReservationTimeRepository;
import roomescape.console.view.InputView;
import roomescape.console.view.OutputView;
import roomescape.domain.ReservationRepository;
import roomescape.domain.ReservationTimeRepository;
import roomescape.service.ReservationService;
import roomescape.service.ReservationTimeService;

public class ConsoleConfig {
    private final ReservationRepository reservationRepository;
    private final ReservationTimeRepository reservationTimeRepository;

    public ConsoleConfig() {
        this.reservationRepository = new ConsoleReservationRepository();
        this.reservationTimeRepository = new ConsoleReservationTimeRepository();
    }

    public ReservationRepository reservationRepository() {
        return reservationRepository;
    }

    public ReservationTimeRepository reservationTimeRepository() {
        return reservationTimeRepository;
    }

    public ReservationService reservationService() {
        return new ReservationService(reservationRepository(), reservationTimeRepository());
    }

    public ReservationTimeService reservationTimeService() {
        return new ReservationTimeService(reservationTimeRepository());
    }

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }
}
