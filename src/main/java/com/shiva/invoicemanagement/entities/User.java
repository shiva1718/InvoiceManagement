package com.shiva.invoicemanagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @Email
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "Role cannot be empty")
    private Role role;

    public User(String username, String encode, String email, Role role) {
        this.username = username;
        this.password = encode;
        this.email = email;
        this.role = role;
    }

}