package com.example.WebService.config;

import com.example.WebService.utils.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        log.info("Bearer Token " + token);
        if (token != null && token.startsWith("Bearer ")) {
        String jwtToken = token.substring(7);
        String getSubject = jwtService.getUserFromToken(jwtToken);
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(getSubject);
        log.info(grantedAuthority.getAuthority());
        log.info(List.of(grantedAuthority).toString());
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(getSubject, jwtToken, List.of(grantedAuthority));
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        }
        else {
            filterChain.doFilter(request, response);
        }
    }
}
