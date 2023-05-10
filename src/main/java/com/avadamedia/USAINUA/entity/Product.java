package com.avadamedia.USAINUA.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private double price;
    private String type;
    @Column(unique = true, length = 1000)
    private String link;
    @Column(name = "image_name", unique = true)
    private String imageName;
}
