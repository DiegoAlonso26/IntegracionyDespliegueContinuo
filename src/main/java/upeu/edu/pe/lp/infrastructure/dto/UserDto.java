package upeu.edu.pe.lp.infrastructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;
import upeu.edu.pe.lp.infrastructure.entity.UserType;

import java.time.LocalDateTime;

public class UserDto {
    private String username;
    @NotBlank(message = "Nombre es requerido")
    private String firstname;
    @NotBlank (message = "Apellido es requerido")
    private String lastName;
    @Email(message = "Debe ingresar un email valido")
    private String email;
    @NotBlank (message = "Dirección es requerido")
    private String address;
    @NotBlank (message = "Celular es requerido")
    private String cellphone;
    @NotBlank (message = "Clave es requerido")
    private String password;




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public UserEntity userDtoToUser(){
        return new UserEntity(null,this.getEmail(),this.getFirstname(), this.getLastName(),this.getEmail(), this.getAddress(), this.getCellphone(), this.getPassword(), UserType.USER, LocalDateTime.now());
    }



}
