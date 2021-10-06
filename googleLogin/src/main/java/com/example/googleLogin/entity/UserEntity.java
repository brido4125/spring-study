package com.example.googleLogin.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "USER_ID",nullable = false)
    private String id;

    @Column(name = "USER_NAME", nullable = false)
    private String name;

    @Column(name = "USER_EMAIL",nullable = false)
    private String email;

    @Column(name = "USER_IMAGE_URL")
    private String imageUrl;

    @Column(name = "token", columnDefinition = "TEXT")
    private String token;

    @Builder
    public User(String id, String name, String email, String imageUrl, String token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.token = token;
    }
}
