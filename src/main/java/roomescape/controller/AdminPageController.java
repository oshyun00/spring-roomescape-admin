package roomescape.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
    @GetMapping
    public String openAdminPage() {
        return "/admin/index";
    }

    @GetMapping("/reservation")
    public String openAdminReservationPage() {
        return "/admin/reservation";
    }

    @GetMapping("/time")
    public String openAdminTimePage() {
        return "/admin/time";
    }
}
