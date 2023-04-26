package com.avadamedia.USAINUA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "shops")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(unique = true)
    private String link;
    @Column(name = "image-name",unique = true)
    private String imageName;
}
