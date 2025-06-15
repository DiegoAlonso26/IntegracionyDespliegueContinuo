package upeu.edu.pe.lp.app.service;

import upeu.edu.pe.lp.infrastructure.entity.UserEntity;
import upeu.edu.pe.lp.infrastructure.entity.UserType;

public class LoginService {
    private final UserService userService;

    public LoginService(UserService userService) {
        this.userService = userService;
    }

    //retorna true si encuentra el user
    public boolean existUser(String email){
        try {
            UserEntity user = userService.findByemail(email);
        }catch(Exception e){
            return false;
        }
        return true;
    }
    //obtenemos el id del usuario
    public Integer getUserId(String email){
        try{
            return userService.findByemail(email).getId();
        }catch (Exception e){
            return 0;
        }
    }
    //obtener tipo de usuario
    public UserType getUserType(String email){
        return userService.findByemail(email).getUserType();
    }
    //obtenemos el user por email
    public UserEntity getuser(String email){
        try{
            return userService.findByemail(email);
        }catch (Exception e){
            return new UserEntity();
        }
    }
    //obtenemos el user por id
    public UserEntity getUser(Integer id){
        try{
            return userService.findById(id);
        }catch (Exception e){
            return new UserEntity();
        }
    }
}
