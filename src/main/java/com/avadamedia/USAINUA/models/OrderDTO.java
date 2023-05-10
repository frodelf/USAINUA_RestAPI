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
    private String transport;
    private String description;
    @URL(message = "Посилання має бути URL-адресою")
    private String link;
    private String trackNumber;
    @NotNull(message = "Вага повина бути вказана")
    @Positive(message = "Вага повинна бути більшою за нуль")
    private double weight;
    @Min(value = 0, message = "Ціна повинна бути більшою або дорівнювати нулю")
    private double price;
    @Positive(message = "Кількість повинна бути більшою за нуль")
    private int number;
    @NotNull(message = "Поле 'only_delivery' не може бути null")
    private boolean isOnlyDelivery;
}
