
package com.learningplanner.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;
    private LocalDateTime createdAt = LocalDateTime.now();
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getPhoneNumber(){return phoneNumber;} public void setPhoneNumber(String phoneNumber){this.phoneNumber=phoneNumber;}
    public String getPassword(){return password;} public void setPassword(String password){this.password=password;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime d){this.createdAt=d;}
}
