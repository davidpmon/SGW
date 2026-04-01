package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model, String error, String logout, Authentication authentication) {
        //ESTO YA LO HACE en el securityconfig y el customsuucesshandler pero se ejecuta despues para evitar que los usuarios
        //puedan logearse despues de ya estar logeados sin cerrar sesion
        if(authentication != null && authentication.isAuthenticated()){
            var roles = authentication.getAuthorities();

            //funciones lambda: funciones anonimas las cuales podemos implementar sin darle nombre
            // dado un parámetro r (que representa un rol del usuario),
            // devuelve true si r.getAuthority() es igual a "ROLE_Administrador"; de lo contrario, devuelve false.
            if(roles.stream().anyMatch(r-> r.getAuthority().equals("ROLE_ADMIN")))
            {
                return "redirect:/admin";
            }
            else if(roles.stream().anyMatch(r-> r.getAuthority().equals("ROLE_EDITOR")))
            {
                return "redirect:/editor";
            }
            else if(roles.stream().anyMatch(r-> r.getAuthority().equals("ROLE_SUPERVISOR")))
            {
                return "redirect:/supervisor";
            }
            return "redirect:/home";
        }

        if (error != null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
        }
        if (logout != null) {
            model.addAttribute("mensaje", "Sesión cerrada correctamente");
        }
        return "login"; // muestra login.html
    }
    @GetMapping("/logout")
    public String logout() {
        // Redirige al login
        return "redirect:/login?logout=true";
    }
}
