/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.lp.infrastructure.adapter;

import org.springframework.stereotype.Repository;
import upeu.edu.pe.lp.app.repository.UserRepository;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;

/**
 *
 * @author Luki
 */
@Repository
public class UserRepositoryImpl implements UserRepository{
    
    private final UserCrudRepository userCrudRepository;

    public UserRepositoryImpl(UserCrudRepository userRepository) {
        this.userCrudRepository = userRepository;
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userCrudRepository.save(user);
    }

    @Override
    public Long count() {
        return userCrudRepository.count();
    }

    @Override
    public Iterable<UserEntity> getUsers() {
        return userCrudRepository.findAll();
    }

    @Override
    public UserEntity findById(Integer id) {
        return userCrudRepository.findById(id).get();
    }

    @Override
    public UserEntity findByemail(String email) {
        return userCrudRepository.findByemail(email).get();
    }



}
