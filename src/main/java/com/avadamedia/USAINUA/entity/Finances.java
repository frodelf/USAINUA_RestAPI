package com.avadamedia.USAINUA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Table(name = "finances")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Finances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Date date;
    private double sum;
    public Finances(Date date, double sum) {
        this.date = date;
        this.sum = sum;
    }
}
