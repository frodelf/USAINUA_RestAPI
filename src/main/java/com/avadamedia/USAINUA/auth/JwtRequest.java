package com.avadamedia.USAINUA.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class JwtRequest {
    @Schema(description = "email", example = "admin@gmail.com")
    private String username;
    @Schema(description = "password", example = "1111")
    private String password;
}
