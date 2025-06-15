package upeu.edu.pe.lp.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//getter And setter
@Data
//constructor lleno
@AllArgsConstructor
//consytuctor vacio
@NoArgsConstructor
@Entity
@Table (name = "usuarios")
public class UserEntity {
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Integer id;
        private String username;
        private String firstname;
        private String lastname;
        private String email;
        private String address;
        private String cellphone;
        private String password;
        @Enumerated(EnumType.STRING)
        private UserType userType;
        
        private LocalDateTime dataCreated;

}

