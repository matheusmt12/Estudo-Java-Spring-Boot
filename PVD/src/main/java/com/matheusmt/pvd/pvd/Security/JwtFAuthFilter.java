package com.matheusmt.pvd.pvd.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private CustonUserDetalheService custonUserDetalheService;

    public JwtFAuthFilter(JwtService jwtService, CustonUserDetalheService custonUserDetalheService) {
        this.jwtService = jwtService;
        this.custonUserDetalheService = custonUserDetalheService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
