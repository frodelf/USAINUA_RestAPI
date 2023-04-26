package com.avadamedia.USAINUA.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "User's address DTO")
public class UserAddressDTO {
    @NotBlank(message = "Назву адреси не було вказано")
    private String addressName;
    @NotBlank(message = "Ім'я не було вказано")
    private String usersName;
    @NotBlank(message = "Прізвище не було вказано")
    private String usersSurname;
    @NotBlank(message = "Телефоний номер не було вказано")
    @Pattern(regexp = "^[0-9]+$", message = "Некоректний телефоний номер")
    private String phone;
    @NotBlank(message = "Назву реніону не було вказано")
    private String region;
    @NotBlank(message = "Назву міста не було вказано")
    private String city;
    @NotBlank(message = "Назву відділення не було вказано")
    private String department;
}
