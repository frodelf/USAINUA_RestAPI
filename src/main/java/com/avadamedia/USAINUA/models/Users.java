package com.avadamedia.USAINUA.models;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String surname;
    private String password;

    @Column(name = "is-man")
    private Boolean isMan;

    @Column(name = "birthday_date")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "e-mail", unique = true)
    private String email;

    private String number;

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

    @ManyToMany
    List<Roles> roles;
}
