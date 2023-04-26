package com.avadamedia.USAINUA.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "Credit card DTO")
public class CreditCardDTO {
    @NotBlank(message = "Номер кредитної карти не був вказаний")
    @Pattern(regexp = "^[0-9]+$", message = "Некоректний номер карти, повинен бути формат \"0000111122223333\"")
    @Length(min = 16, max = 16, message = "Кількість символів номеру карти повинна бути від 16 до 20")
    private String number;
    @NotBlank(message = "Період дії кредитної карти не був вказаний")
    @Pattern(regexp = "^[0-9 /]+$", message = "Некоректний період дії карти, повинен бути формат \"00/11\"")
    @Length(min = 5, max = 5, message = "Кількість символів періоду карти повинна бути рівно 5 символів")
    private String period;
    @NotBlank(message = "CVV код не був вказаний")
    @Pattern(regexp = "^[0-9]+$", message = "Некоректний CVV код карти, повинен бути формат \"123\"")
    @Length(min = 3, max = 3, message = "Кількість символів CVV коду  повинна бути рівно 3 символи")
    private String cvv;
}
