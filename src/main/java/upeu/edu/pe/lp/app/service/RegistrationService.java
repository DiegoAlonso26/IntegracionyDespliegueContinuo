package upeu.edu.pe.lp.app.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;

public class RegistrationService {
    private final UserService userServices;
     private final PasswordEncoder passwordEncoder;


    public RegistrationService(UserService userServices, PasswordEncoder passwordEncoder) {
        this.userServices = userServices;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserEntity user){
       user.setPassword(passwordEncoder.encode(user.getPassword()));

        userServices.saveUser(user);
    }
}
