package com.avadamedia.USAINUA.models;

//import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "User's address DTO")
public class UserAddressDTO {
    @NotBlank(message = "Назву адреси не було вказано")
    @Schema(defaultValue = "Home")
    @Length(min = 5, max = 50, message = "Поле повинно містити від 5 до 50 символів")
    private String addressName;
    @NotBlank(message = "Ім'я не було вказано")
    @Schema(defaultValue = "Denis")
    @Length(min = 5, max = 50, message = "Поле повинно містити від 5 до 50 символів")
    private String usersName;
    @NotBlank(message = "Прізвище не було вказано")
    @Schema(defaultValue = "Surname")
    @Length(min = 5, max = 50, message = "Поле повинно містити від 5 до 50 символів")
    private String usersSurname;
    @NotBlank(message = "Телефоний номер не було вказано")
    @Pattern(regexp = "^[0-9]+$", message = "Некоректний телефоний номер")
    @Schema(defaultValue = "0987654321")
    private String phone;
    @NotBlank(message = "Назву реніону не було вказано")
    @Schema(defaultValue = "Rivne")
    @Length(min = 5, max = 50, message = "Поле повинно містити від 5 до 50 символів")
    private String region;
    @NotBlank(message = "Назву міста не було вказано")
    @Schema(defaultValue = "Rivne")
    @Length(min = 5, max = 50, message = "Поле повинно містити від 5 до 50 символів")
    private String city;
    @NotBlank(message = "Назву відділення не було вказано")
    @Schema(defaultValue = "none")
    @Length(min = 5, max = 50, message = "Поле повинно містити від 5 до 50 символів")
    private String department;
}
