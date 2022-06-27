package com.codegym.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String avatar;

    private String phoneNumber;


    private String email;

    private String birthDay;

    private String address;

    @OneToOne
    private User user;


    public UserInfo(String name, String avatar, String phoneNumber, String email, String birthDay, String address, User user) {
        this.name = name;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDay = birthDay;
        this.address = address;
        this.user = user;
    }

    public UserInfo(String name, String avatar, String phoneNumber, String birthDay, String address, User user) {
        this.name = name;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.address = address;
        this.user = user;
    }

    public UserInfo(String name, String phoneNumber, String birthDay, String address, User user) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.address = address;
        this.user = user;
    }

    public UserInfo(String name, String phoneNumber, String birthDay, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.address = address;
    }


}
