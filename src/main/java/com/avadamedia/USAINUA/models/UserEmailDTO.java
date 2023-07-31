package com.avadamedia.USAINUA.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "email")
public class UserEmailDTO {
    @NotBlank(message = "Поле не може бути пустим")
    @Email
    @Length(min = 5, max = 50, message = "Поле повинно містити від 5 до 50 символів")
    @Schema(defaultValue = "20denisderkach04@gmail.com")
    private String email;
}
