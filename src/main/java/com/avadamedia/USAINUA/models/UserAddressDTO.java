package com.avadamedia.USAINUA.models;

//import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "User's address DTO")
public class UserAddressDTO {
    @NotBlank(message = "Назву адреси не було вказано")
    @Schema(defaultValue = "Home")
    private String addressName;
    @NotBlank(message = "Ім'я не було вказано")
    @Schema(defaultValue = "Denis")
    private String usersName;
    @NotBlank(message = "Прізвище не було вказано")
    @Schema(defaultValue = "Surname")
    private String usersSurname;
    @NotBlank(message = "Телефоний номер не було вказано")
    @Pattern(regexp = "^[0-9]+$", message = "Некоректний телефоний номер")
    @Schema(defaultValue = "0987654321")
    private String phone;
    @NotBlank(message = "Назву реніону не було вказано")
    @Schema(defaultValue = "Rivne")
    private String region;
    @NotBlank(message = "Назву міста не було вказано")
    @Schema(defaultValue = "Rivne")
    private String city;
    @NotBlank(message = "Назву відділення не було вказано")
    @Schema(defaultValue = "none")
    private String department;
}
