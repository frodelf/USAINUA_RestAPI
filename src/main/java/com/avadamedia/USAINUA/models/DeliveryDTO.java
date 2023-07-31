package com.avadamedia.USAINUA.models;

import com.avadamedia.USAINUA.entity.AdditionalService;
import com.avadamedia.USAINUA.enums.Transport;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DeliveryDTO {
    @NotNull(message = "Поле не може бути пустим")
    @Schema(defaultValue = "10")
    private Double weight;
    @NotNull(message = "Поле не може бути пустим")
    @Schema(defaultValue = Transport.PLANE)
    private String transport;
    private List<AdditionalService> additionalServices;
}
