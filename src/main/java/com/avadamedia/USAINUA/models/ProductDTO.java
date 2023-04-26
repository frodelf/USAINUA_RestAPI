package com.avadamedia.USAINUA.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
@Schema(description = "Product DTO")
public class ProductDTO {
    @NotBlank(message = "Поле 'name' не може бути порожнім")
    private String name;

    @NotNull(message = "Поле 'price' не може бути порожнім")
    @Min(value = 0, message = "Ціна не може бути від'ємною")
    private double price;

    @NotBlank(message = "Поле тип продукту не може бути порожнім")
    @Pattern(regexp = "^(Clothes|Sport|Gadgets|Another)$", message = "Тип продукту може мати тільки значення: 'Clothes', 'Sport', 'Gadgets', 'Another'")
    private String type;

    @NotBlank(message = "Посилання на продукт не може бути порожнім")
    @URL(message = "Посилання має бути URL-адресою")
    private String link;

    @NotBlank(message = "Фотографія для продукту не може бути порожньою")
    private String imageName;
}
