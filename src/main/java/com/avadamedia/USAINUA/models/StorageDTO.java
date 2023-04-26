package com.avadamedia.USAINUA.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "Storage DTO")
public class StorageDTO {
    @NotBlank(message = "Ім'я складу не може бути порожнім")
    private String fullName;
    @NotBlank(message = "Назва вулиці не може бути порожньою")
    private String street;
    @NotBlank(message = "Назва міста не може бути порожньою")
    private String city;
    @NotBlank(message = "Назва штату не може бути порожньою")
    private String state;
    @NotBlank(message = "Індекс складу не може бути порожнім")
    @Pattern(regexp = "^[0-9]+$", message = "Некоректний індекс складу")
    private String index;
    @NotBlank(message = "Телефон складу не може бути порожнім")
    @Pattern(regexp = "^[0-9]+$", message = "Некоректний телефон складу")
    private String phone;
}
