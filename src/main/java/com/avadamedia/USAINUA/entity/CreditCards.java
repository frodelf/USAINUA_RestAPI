package com.avadamedia.USAINUA.entity;

import com.avadamedia.USAINUA.models.CreditCardDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "credit_cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "cards_number", length = 16)
    private String cardsNumber;
    @Column(name = "validity_period", length = 5)
    private String validityPeriod;
    @Column(name = "CVV", length = 3)
    private String cvv;
    public CreditCards(CreditCardDTO creditCardDTO) {
        this.cardsNumber = creditCardDTO.getNumber();
        this.validityPeriod = creditCardDTO.getPeriod();
        this.cvv = creditCardDTO.getCvv();
    }
}
