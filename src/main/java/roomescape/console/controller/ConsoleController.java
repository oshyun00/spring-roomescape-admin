package roomescape.console.controller;

import roomescape.console.view.Command;
import roomescape.console.view.InputView;
import roomescape.console.view.OutputView;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;
import roomescape.dto.ReservationTimeRequest;
import roomescape.dto.ReservationTimeResponse;
import roomescape.service.ReservationService;
import roomescape.service.ReservationTimeService;

public class ConsoleController {
    private final OutputView outputView;
    private final InputView inputView;
    private final ReservationTimeService reservationTimeService;
    private final ReservationService reservationService;

    public ConsoleController(OutputView outputView,
                             InputView inputView,
                             ReservationTimeService reservationTimeService,
                             ReservationService reservationService) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.reservationTimeService = reservationTimeService;
        this.reservationService = reservationService;
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
        ReservationResponse response = reservationService.createReservation(request);
        outputView.printCreateReservationSuccessMessage(response);
    }

    private void deleteReservation() {
        Long id = inputView.readDeleteReservationId();
        reservationService.deleteReservation(id);
        outputView.printDeleteReservationSuccessMessage();
    }

    private void getReservationTimes() {
        outputView.printAllReservationTimes(reservationTimeService.getAllReservationTimes());
    }

    private void createReservationTime() {
        ReservationTimeRequest request = inputView.readReservationTimeRequest();
        ReservationTimeResponse response = reservationTimeService.createReservationTime(request);
        outputView.printCreateTimeSuccessMessage(response);
    }

    private void deleteReservationTime() {
        Long id = inputView.readDeleteReservationTimeId();
        reservationTimeService.deleteReservationTime(id);
        outputView.printDeleteTimeSuccessMessage();
    }
}