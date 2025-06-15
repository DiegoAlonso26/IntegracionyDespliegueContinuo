package upeu.edu.pe.lp.infrastructure.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import upeu.edu.pe.lp.app.service.LogoutService;

@Controller
@RequestMapping("/close")
public class LogoutController {
    private final LogoutService logoutService;

    public LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @GetMapping
    public String logout(HttpSession httpSession){
        logoutService.logout(httpSession);

        return "redirect:/home";
    }
}
