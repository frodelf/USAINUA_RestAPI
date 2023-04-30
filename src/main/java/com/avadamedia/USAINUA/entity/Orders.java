package com.avadamedia.USAINUA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String status;
    private String transport;
    private String description;
    private String link;
    @Column(name = "track_number")
    private String trackNumber;
    private Double weight;
    private Double price;
    @Column(name = "total_price")
    private Double totalPrice;
    private int number;
    @Column(name = "only_delivery")
    private boolean isOnlyDelivery;
    @Column(name = "data_registration")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataRegistration;
    @Column(name = "date_receiving")
    private Date dateReceiving;
    @ManyToMany
    private List<AdditionalServices> additionalServices;
    @ManyToOne
    UsersAddress usersAddresses;
}
