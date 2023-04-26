package com.avadamedia.USAINUA.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@Schema(description = "User DTO")
public class UserDTO {
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Дата дня народження не була вказана")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @NotBlank(message = "Назва користувача не була вказана")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я і]+$", message = "Некоректне ім'я користувача")
    private String name;
    @Schema(description = "User surname", example = "surname")
    @NotBlank(message = "Прізвище користувача не було вказано")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я і]+$", message = "Некоректне прізвище користувача")
    private String surname;
    @NotNull(message = "Стать користувача не була вказана")
    private Boolean isMan;
    @Email(message = "Електрона адреса не коректна")
    @NotBlank(message = "Електроний адрес не був вказаний")
    private String email;
    @NotBlank(message = "Телефоний номер не був вказаний")
    @Pattern(regexp = "^[0-9]+$", message = "Некоректний номер телефону")
    private String phone;
}
