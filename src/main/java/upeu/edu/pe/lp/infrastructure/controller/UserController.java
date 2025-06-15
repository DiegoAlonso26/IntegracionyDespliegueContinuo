package upeu.edu.pe.lp.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import upeu.edu.pe.lp.app.service.UserService;

@Controller
public class UserController {
        private final UserService userService;

        public UserController(UserService userService) {
            this.userService = userService;
        }




    }
