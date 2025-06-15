package upeu.edu.pe.lp.infrastructure.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import upeu.edu.pe.lp.app.service.RegistrationService;
import upeu.edu.pe.lp.infrastructure.dto.AdminDto;
import upeu.edu.pe.lp.infrastructure.dto.UserDto;

@Controller
@RequestMapping("/register-admin")
public class RegisterAdminController {
    private final RegistrationService registrationService;
    private final Logger log = LoggerFactory.getLogger(RegisterController.class);

    public RegisterAdminController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String showRegisterForm(AdminDto adminDto) {

        return "admin/register"; //
    }

    @PostMapping
    public String registerUser(@Valid AdminDto adminDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
//        user.setDateCreated(LocalDateTime.now());
//        user.setUserType(UserType.USER);
//        user.setUsername(user.getEmail());

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(
                    e->{ log.info( "Error {}", e.getDefaultMessage() ); }
            );
            return "/login/register";
        }
        registrationService.register(adminDto.userDtoToUser());
        redirectAttributes.addFlashAttribute("success", "Usuario creado correctamente");


        // Agregar un retraso de 1 segundo (1000 milisegundos) antes de redirigir
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "redirect:/login";
    }

}
