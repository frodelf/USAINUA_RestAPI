package com.avadamedia.USAINUA.models;

//import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@Schema(description = "User DTO")
public class UserDTO {
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Дата дня народження не була вказана")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(defaultValue = "2020-12-12")
    private Date birthday;
    @NotBlank(message = "Назва користувача не була вказана")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я і]+$", message = "Некоректне ім'я користувача")
    @Schema(defaultValue = "Denis")
    private String name;
    @NotBlank(message = "Прізвище користувача не було вказано")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я і]+$", message = "Некоректне прізвище користувача")
    @Schema(defaultValue = "Surname")
    private String surname;
    @NotNull(message = "Стать користувача не була вказана")
    @Schema(defaultValue = "true")
    private Boolean isMan;
    @Email(message = "Електрона адреса не коректна")
    @NotBlank(message = "Електроний адрес не був вказаний")
    @Schema(defaultValue = "20denisderkach04@gmail.com")
    private String email;
    @NotBlank(message = "Телефоний номер не був вказаний")
    @Pattern(regexp = "^[0-9]+$", message = "Некоректний номер телефону")
    @Schema(defaultValue = "0678678678")
    private String phone;
}
