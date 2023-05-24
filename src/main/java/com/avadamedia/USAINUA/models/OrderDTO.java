package com.avadamedia.USAINUA.models;

//import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
@Schema(description = "Order DTO")
public class OrderDTO {
    @NotBlank(message = "Транспортне засіб не може бути порожнім")
    @Pattern(regexp = "^(plane|ship|another)$", message = "Транспорт не коректний")
    @Schema(defaultValue = "plane")
    private String transport;
    @Schema(defaultValue = "description")
    private String description;
    @URL(message = "Посилання має бути URL-адресою")
    @Schema(defaultValue = "https://chat.openai.com/")
    private String link;
    @Schema(defaultValue = "0000")
    private String trackNumber;
    @NotNull(message = "Вага повина бути вказана")
    @Positive(message = "Вага повинна бути більшою за нуль")
    @Schema(defaultValue = "12")
    private double weight;
    @Min(value = 0, message = "Ціна повинна бути більшою або дорівнювати нулю")
    @Schema(defaultValue = "12")
    private double price;
    @Positive(message = "Кількість повинна бути більшою за нуль")
    @Schema(defaultValue = "1")
    private int number;
    @NotNull(message = "Поле 'only_delivery' не може бути null")
    @Schema(defaultValue = "true")
    private boolean isOnlyDelivery;
}
