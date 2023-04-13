package com.avadamedia.USAINUA.jwt;
import com.avadamedia.USAINUA.models.Roles;
import com.avadamedia.USAINUA.models.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtEntityFactory {

    public static JwtEntity create(Users user) {
        return new JwtEntity(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPassword(),
                getAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(List<Roles> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}