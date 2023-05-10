package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.Role;
import com.avadamedia.USAINUA.repositories.RolesRepository;
import com.avadamedia.USAINUA.services.AuthService;
import com.avadamedia.USAINUA.auth.JwtRequest;
import com.avadamedia.USAINUA.auth.JwtResponse;
import com.avadamedia.USAINUA.entity.User;
import com.avadamedia.USAINUA.repositories.UsersRepository;
import com.avadamedia.USAINUA.services.impl.RolesServiceImpl;
import com.avadamedia.USAINUA.util.EmailUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthService authService;
    private final UsersRepository usersRepository;
    private final RolesServiceImpl rolesService;
    @GetMapping("/get-password")
    @Operation(summary = "Get password for user by email")
    public void getPassword(@RequestParam("email")String email){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        int password = (int) (Math.random() * 9000 + 1000);
        EmailUtil.sendEmail("20denisderkach04@gmail.com", email, password);
        Optional<User> users = usersRepository.findByEmail(email);
        if(users.isEmpty()){
            User user1 = new User();
            user1.setEmail(email);
            user1.setPassword(passwordEncoder.encode(String.valueOf(password)));
            List<Role> roles = new ArrayList<>();
            roles.add(rolesService.getById(1));
            user1.setRoles(roles);
            usersRepository.save(user1);
        }
        else {
            users.get().setPassword(passwordEncoder.encode(String.valueOf(password)));
            usersRepository.save(users.get());
        }
    }
    @Operation(summary = "Authorization user")
    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest loginRequest){
        return authService.login(loginRequest);
    }
    @PostMapping("/refresh")
    @Operation(summary = "Update the access token")
    public JwtResponse refresh(
            @Parameter(description = "Refresh token")
            @RequestHeader String refreshToken) {
        log.info(refreshToken);
        return authService.refresh(refreshToken);
    }
}
