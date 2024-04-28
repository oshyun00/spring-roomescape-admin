package roomescape.console.controller;

import roomescape.console.view.Command;
import roomescape.console.view.InputView;
import roomescape.console.view.OutputView;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationTimeRequest;
import roomescape.service.ReservationService;
import roomescape.service.ReservationTimeService;

public class ConsoleController {
    private final OutputView outputView;
    private final InputView inputView;
    private final ReservationService reservationService;
    private final ReservationTimeService reservationTimeService;

    public ConsoleController(OutputView outputView, InputView inputView,
                             ReservationService reservationService,
                             ReservationTimeService reservationTimeService) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.reservationService = reservationService;
        this.reservationTimeService = reservationTimeService;
    }

    public void run() {
        outputView.printStartMessage();
        outputView.printAdminFunction();
        Command command = inputView.readCommand();

        while (command != Command.END) {
            switch (command) {
                case READ_ALL_RESERVATIONS -> getReservations();
                case CREATE_RESERVATION -> createReservation();
                case DELETE_RESERVATION -> deleteReservation();
                case READ_ALL_RESERVATION_TIMES -> getReservationTimes();
                case CREATE_RESERVATION_TIME -> createReservationTime();
                case DELETE_RESERVATION_TIME -> deleteReservationTime();
            }
            outputView.printAdminFunction();
            command = inputView.readCommand();
        }
    }

    private void getReservations() {
        outputView.printAllReservations(reservationService.getAllReservations());
    }

    private void createReservation() {
        ReservationRequest request = inputView.readReservationRequest();
        reservationService.createReservation(request);
    }

    private void deleteReservation() {
        Long id = inputView.readDeleteReservationId();
        reservationService.deleteReservation(id);
    }

    private void getReservationTimes() {
        outputView.printAllReservationTimes(reservationTimeService.getAllReservationTimes());
    }

    private void createReservationTime() {
        ReservationTimeRequest request = inputView.readReservationTimeRequest();
        reservationTimeService.createReservationTime(request);
    }

    private void deleteReservationTime() {
        Long id = inputView.readDeleteReservationTimeId();
        reservationTimeService.deleteReservationTime(id);
    }
}
