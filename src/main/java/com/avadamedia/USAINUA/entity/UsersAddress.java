package com.avadamedia.USAINUA.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "address_name")
    private String addressName;
    @Column(name = "users_name")
    private String usersName;
    @Column(name = "users_surname")
    private String usersSurname;
    private String phone;
    private String region;
    private String city;
    private String department;
}
