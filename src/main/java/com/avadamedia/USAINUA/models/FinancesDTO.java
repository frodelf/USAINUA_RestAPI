package com.avadamedia.USAINUA.models;

//import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@Schema(description = "Finances DTO")
public class FinancesDTO {
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Дата оплачення посилки(Finances) не була вказана")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(defaultValue = "2000-12-12")
    private Date date;

    @DecimalMin(value = "0.0", message = "Загальна ціна посилки (Finances) повинна бути більша нуля")
    @NotNull(message = "Ціна посилки (Finances) не була вказана")
    @Schema(defaultValue = "12")
    private double sum;
}
