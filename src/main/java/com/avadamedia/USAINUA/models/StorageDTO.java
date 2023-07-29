package com.avadamedia.USAINUA.models;

//import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "Storage DTO")
public class StorageDTO {
    @NotBlank(message = "Ім'я складу не може бути порожнім")
    @Schema(defaultValue = "USA")
    @Length(min = 5, max = 50, message = "Поле повинно містити від 5 до 50 символів")
    private String fullName;
    @NotBlank(message = "Назва вулиці не може бути порожньою")
    @Schema(defaultValue = "street")
    @Length(min = 5, max = 50, message = "Поле повинно містити від 5 до 50 символів")
    private String street;
    @NotBlank(message = "Назва міста не може бути порожньою")
    @Schema(defaultValue = "Rivne")
    @Length(min = 5, max = 50, message = "Поле повинно містити від 5 до 50 символів")
    private String city;
    @NotBlank(message = "Назва штату не може бути порожньою")
    @Schema(defaultValue = "state")
    @Length(min = 5, max = 50, message = "Поле повинно містити від 5 до 50 символів")
    private String state;
    @NotBlank(message = "Індекс складу не може бути порожнім")
    @Pattern(regexp = "^[0-9]+$", message = "Некоректний індекс складу")
    @Schema(defaultValue = "0000")
    private String index;
    @NotBlank(message = "Телефон складу не може бути порожнім")
    @Pattern(regexp = "^[0-9]+$", message = "Некоректний телефон складу")
    @Schema(defaultValue = "380696969696")
    private String phone;
}
