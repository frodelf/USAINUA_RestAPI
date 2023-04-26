package com.avadamedia.USAINUA.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@Schema(description = "Finances DTO")
public class FinancesDTO {
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Дата оплачення посилки(Finances) не була вказана")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @DecimalMin(value = "0.0", message = "Загальна ціна посилки (Finances) повинна бути більша нуля")
    @NotNull(message = "Ціна посилки (Finances) не була вказана")
    private double sum;
}
