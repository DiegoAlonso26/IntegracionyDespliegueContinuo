/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.lp.app.service;

import upeu.edu.pe.lp.app.repository.UserRepository;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;


public class UserService {
   
private final UserRepository userRepository;

    public Iterable<UserEntity> getUsers(){
        return userRepository.getUsers();
    }
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public UserEntity saveUser(UserEntity user) {
        return userRepository.saveUser(user);
    }
    
    public UserEntity findById(Integer id) {
        return userRepository.findById(id);
    }

    public UserEntity findByemail(String email) {
        return userRepository.findByemail(email);
    }
    public Long countTotalUsers(){
        Long countValue = userRepository.count();
        if (countValue != null) {
            return countValue;
        } else {
            return 0L;
        }
    }
}
