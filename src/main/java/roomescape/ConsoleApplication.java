package roomescape;

import roomescape.console.configuration.ConsoleConfig;
import roomescape.console.controller.ConsoleController;

public class ConsoleApplication {
    public static void main(String[] args) {
        ConsoleConfig consoleConfig = new ConsoleConfig();
        ConsoleController consoleController = new ConsoleController(
                consoleConfig.outputView(),
                consoleConfig.inputView(),
                consoleConfig.reservationService(),
                consoleConfig.reservationTimeService()
        );
        consoleController.run();
    }
}
