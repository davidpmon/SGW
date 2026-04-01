package com.example.demo.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        String redirectUrl = "/login"; // por defecto

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            String role = auth.getAuthority(); // ej: ROLE_Administrador

            if (role.equals("ROLE_ADMIN")) {
                redirectUrl = "/admin";
                break;
            } else if (role.equals("ROLE_EDITOR")) {
                redirectUrl = "/editor";
                break;
            } else if (role.equals("ROLE_SUPERVISOR")) {
                redirectUrl = "/supervisor";
                break;
            }
        }

        response.sendRedirect(redirectUrl);
        System.out.println("holaaaa"+ authentication.getAuthorities());
    }
}
