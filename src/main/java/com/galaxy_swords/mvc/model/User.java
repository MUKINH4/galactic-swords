package com.galaxy_swords.mvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Data
public class User {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Email
    private String email;

    private String password;

}
