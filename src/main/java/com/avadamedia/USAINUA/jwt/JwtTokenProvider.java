package com.avadamedia.USAINUA.jwt;

import com.avadamedia.USAINUA.auth.JwtResponse;
import com.avadamedia.USAINUA.entity.Roles;
import com.avadamedia.USAINUA.entity.Users;
import com.avadamedia.USAINUA.props.JwtProperties;
import com.avadamedia.USAINUA.services.impl.UsersServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private final UserDetailsService userDetailsService;
    private final UsersServiceImpl usersService;
    private Key key;

    @PostConstruct
    public void init() {

        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String createAccessToken(Long userId, String username, List<Roles> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id", userId);
        claims.put("roles", roles);
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getAccess());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    public String createRefreshToken(Long userId, String username) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id", userId);
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getRefresh());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    public JwtResponse refreshUserTokens(String refreshToken) {
        JwtResponse jwtResponse = new JwtResponse();
        if (!validateToken(refreshToken)) {
            throw new RuntimeException();
        }
        Long userId = Long.valueOf(getId(refreshToken));
        Users user = usersService.getById(userId);
        jwtResponse.setId(userId);
        jwtResponse.setUsername(user.getEmail());
        jwtResponse.setAccessToken(createAccessToken(userId, user.getEmail(), user.getRoles()));
        jwtResponse.setRefreshToken(createRefreshToken(userId, user.getEmail()));
        return jwtResponse;
    }

    public boolean validateToken(String token) {
        Jws<Claims> claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

    private String getId(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id")
                .toString();
    }

    private String getUsername(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Authentication getAuthentication(String token) {
        String username = getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}