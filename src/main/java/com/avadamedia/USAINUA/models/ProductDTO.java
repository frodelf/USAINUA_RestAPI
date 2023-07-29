package com.avadamedia.USAINUA.models;

//import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Data
@Schema(description = "Product DTO")
public class ProductDTO {
    @NotBlank(message = "Поле 'name' не може бути порожнім")
    @Schema(defaultValue = "ball")
    @Length(min = 5, max = 50, message = "Поле повинно містити від 5 до 50 символів")
    private String name;

    @NotNull(message = "Поле 'price' не може бути порожнім")
    @Min(value = 0, message = "Ціна не може бути від'ємною")
    @Schema(defaultValue = "12.5")
    private double price;

    @NotBlank(message = "Поле тип продукту не може бути порожнім")
    @Pattern(regexp = "^(Clothes|Sport|Gadgets|Another)$", message = "Тип продукту може мати тільки значення: 'Clothes', 'Sport', 'Gadgets', 'Another'")
    @Schema(defaultValue = "Sport")
    private String type;

    @NotBlank(message = "Посилання на продукт не може бути порожнім")
    @URL(message = "Посилання має бути URL-адресою")
    @Schema(defaultValue = "https://chat.openai.com/")
    private String link;

    @NotBlank(message = "Фотографія для продукту не може бути порожньою")
    @Schema(defaultValue = "imageName")
    private String imageName;
}
