package com.avadamedia.USAINUA.config;

import com.avadamedia.USAINUA.entity.UserDetailsImpl;
import com.avadamedia.USAINUA.services.impl.UsersServiceImpl;
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UsersServiceImpl usersService;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> new UserDetailsImpl().getUserDetailsByUsers(usersService.getByEmail(username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI()
//            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//            .components(
//                new Components()
//                    .addSecuritySchemes("bearerAuth",
//                    new SecurityScheme()
//                        .type(SecurityScheme.Type.HTTP)
//                        .scheme("bearer")
//                        .bearerFormat("JWT")
//                    )
//                )
//            .info(new Info()
//                .title("Task list API")
//                .description("Demo Spring Boot application")
//                .version("1.0")
//            );
//    }
}
