package com.example.demo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentController {
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('VER_DOCUMENTO')")
    public String test() {
        return "PERMISO CONCEDIDO";
    }
}
 