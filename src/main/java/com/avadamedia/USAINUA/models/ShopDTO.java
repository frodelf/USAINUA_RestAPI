package com.avadamedia.USAINUA.models;

//import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
@Schema(description = "Shop DTO")
public class ShopDTO {
    @URL(message = "Посилання на магазин має бути URL-адресою")
    @NotBlank(message = "Посилання на продукт не може бути порожнім")
    @Schema(defaultValue = "https://chat.openai.com/")
    private String link;
    @NotBlank(message = "Фотографія магазину не може бути порожньою")
    @Schema(defaultValue = "imageName")
    private String imageName;
}
