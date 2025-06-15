/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.lp.infrastructure.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import upeu.edu.pe.lp.app.service.RegistrationService;
import upeu.edu.pe.lp.app.service.UserService;
import upeu.edu.pe.lp.infrastructure.dto.UserDto;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;

/**
 *
 * @author Luki
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    private final RegistrationService registrationService;
    private final Logger log = LoggerFactory.getLogger(RegisterController.class);

    public RegisterController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String showRegisterForm(UserDto userDto) {

        return "login/register"; //
    }

    @PostMapping
    public String registerUser(@Valid UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
//        user.setDateCreated(LocalDateTime.now());
//        user.setUserType(UserType.USER);
//        user.setUsername(user.getEmail());

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(
                    e->{ log.info( "Error {}", e.getDefaultMessage() ); }
            );
            return "/login/register";
        }
        registrationService.register(userDto.userDtoToUser());
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
