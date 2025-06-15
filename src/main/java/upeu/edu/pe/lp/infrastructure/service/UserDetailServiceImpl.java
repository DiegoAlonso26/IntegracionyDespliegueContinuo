package upeu.edu.pe.lp.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import upeu.edu.pe.lp.app.service.LoginService;
import jakarta.servlet.http.HttpSession;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    private final LoginService loginService;
    private final Integer USER_NOT_FOUND = 0;
    @Autowired
    private HttpSession httpSession;

    public UserDetailServiceImpl(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Integer idUser = loginService.getUserId(username);
        if (idUser != USER_NOT_FOUND) {
            UserEntity user = loginService.getuser(username);
            httpSession.setAttribute("iduser", user.getId());
            httpSession.setAttribute("name", user.getFirstname());
            return org.springframework.security.core.userdetails.User.builder().username(user.getUsername()).password(user.getPassword()).roles(user.getUserType().name()).build();
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado ");
        }

    }
}
