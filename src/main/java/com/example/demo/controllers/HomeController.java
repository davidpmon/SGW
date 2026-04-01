package com.example.demo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html
    }
    @GetMapping("/terminos")
    public String terminos()
    {
        return "terminos";
    }
    @GetMapping("/admin")
    public String adminHome() {
        return "admin/home"; // templates/admin/home.html
    }

    @GetMapping("/editor")
    public String editorHome() {
        return "editor/home"; // templates/editor/home.html
    }

    @GetMapping("/supervisor")
    public String supervisorHome() {
        return "supervisor/home"; // templates/supervisor/home.html
    }
   //revision futura
   @GetMapping("/editor/terminos")
    public String mostrarTerminosEditor() {
        return "editor/terminos_medico"; // ruta dentro de templates
    }

    @GetMapping("/terminos/admin")
    public String terminosAdmin() {
        return "Admin/terminos";
    }
    @GetMapping("/admin/home")
    public String homeAdmin() {
        return "admin/home";
    }
    @GetMapping("/terminos/supervisor")
    public String terminosSupervisor() {
        return "supervisor/terminos";
    }
    @GetMapping("/403")
    public String accesoDenegado() {
        return "index"; // 403.html
    }
    /*@GetMapping("/supervisor/home")
    public String homeSupervisor() {
        return "supervisor/home";
    }*/
}
