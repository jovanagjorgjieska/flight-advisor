package com.example.flightadvisor.config.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.flightadvisor.exceptions.PasswordsDoNotMatchException;
import com.example.flightadvisor.model.User;
import com.example.flightadvisor.model.dto.UserDetailsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public static final long EXPIRATION_TIME = 864_000_000; //10 days
    public static final String SECRET = "s3cr3tkey";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User creds = null;
        try {
            creds = new ObjectMapper().readValue(request.getInputStream(), User.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        if(creds == null){
            throw new UsernameNotFoundException("Invalid credentials");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(creds.getUsername());
//        if(!passwordEncoder.matches(creds.getPassword(), userDetails.getPassword())){
//            throw new PasswordsDoNotMatchException();
//        }
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(),creds.getPassword(),
                userDetails.getAuthorities()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User userDetails = (User) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(new ObjectMapper().writeValueAsString(UserDetailsDto.of(userDetails)))
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET));
//        response.addHeader("Authorization", "Bearer "+token);
        response.setHeader("token", token);
    }
}
