package upeu.edu.pe.lp.infrastructure.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upeu.edu.pe.lp.app.domain.Authenticate;
import upeu.edu.pe.lp.app.model.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    Authenticate authenticate;

    public AuthController(Authenticate authenticate) {
        this.authenticate = authenticate;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody LoginRequest loginRequest){
        authenticate.sendMessageByUser(loginRequest.getEmailUser(), loginRequest.getMessage());

        return ResponseEntity.ok()
                .body("Hola...");

    }

}
