package com.avadamedia.USAINUA.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
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
    @Schema(description = "User id", example = "1")
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
    @Column(name = "e-mail")
    private String email;
    private String phone;
    @OneToMany
    List<Orders> orders;
    @OneToMany
    List<Storage> compositions;
    @OneToMany
    List<CreditCards> creditCards;
    @OneToMany
    List<Finances> finances;
    @OneToMany
    List<UsersAddress> usersAddresses;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Roles> roles;
}