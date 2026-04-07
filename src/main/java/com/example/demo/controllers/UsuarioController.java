package com.example.demo.controllers;

import com.example.demo.models.Usuario;
import com.example.demo.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Importante para enviar datos a la vista
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/usuarios") // Ruta base para este controlador
public class UsuarioController {

    private final UsuarioService usuarioService;

    // Constructor, inyección de dependencias
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // localhost:8080/admin/usuarios
    @GetMapping
    public String listarUsuarios(@RequestParam(name = "keyword", required = false) String keyword,
                                 @RequestParam(name = "rol", required = false) String rol, Model model) {
        // Si hay keyword, el servicio filtra; si no, trae todos.
        model.addAttribute("usuarios", usuarioService.buscarUsuarios(keyword, rol));
        model.addAttribute("keyword", keyword);// Para que el texto no se borre del input al buscar
        model.addAttribute("rolSeleccionado", rol); // para el filtrado de roles
        return "admin/usuarios";
    }
    @GetMapping("/usuarioNuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/usuarioNuevo";
    }

}