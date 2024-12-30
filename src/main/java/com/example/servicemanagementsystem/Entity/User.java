package com.example.servicemanagementsystem.Entity;

import com.example.servicemanagementsystem.dto.UserDto;
import com.example.servicemanagementsystem.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String lastname;

    private String phone;

     @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserDto getDto(){
        UserDto userDto=new UserDto();
        userDto.setId(id);
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setRole(role);

         return userDto;

        

    }
  
    
}
