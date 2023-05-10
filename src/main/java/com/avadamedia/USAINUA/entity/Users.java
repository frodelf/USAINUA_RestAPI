package com.avadamedia.USAINUA.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private Long id;
    private String name;
    private String surname;
    private String password;
    private double money;
    private Boolean isMan;
    private Date birthday;
    private String email;
    private String phone;
    @OneToMany
    List<Orders> orders;
    @OneToMany
    List<CreditCards> creditCards;
    @OneToMany
    List<Finances> finances;
    @OneToMany
    List<UsersAddress> usersAddresses;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Roles> roles;
}