package org.example.hydbackend.auth.entity;
import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name ="firstname",nullable = false)
    private String firstname;
    @Column(name ="middlename", nullable = true)
    private String middlename;
    @Column(name = "lastname",nullable = false)
    private String lastname;
    @Column(name = "email", nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private Role role;
}
