package com.avadamedia.USAINUA.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "additional_services")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(unique = true)
    private String name;
    private double price;
}
