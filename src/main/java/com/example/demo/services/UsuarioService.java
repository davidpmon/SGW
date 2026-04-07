package com.example.demo.services;

import com.example.demo.models.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Usuario> buscarPorCedula(String cedula) {
        return usuarioRepository.findByUsuario(cedula);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    //busqueda y filtrado por roles desde el panel de ADMIN, keyword es la palabra de filtrado que busca el admin
    public List<Usuario> buscarUsuarios(String keyword, String rol) {
        if (rol != null && !rol.isEmpty()) {
            return usuarioRepository.findByRoles_NombreRol(rol);//filtrado por roles
        }
        if (keyword != null && !keyword.isEmpty()) {
            return usuarioRepository.findByUsuarioContainingIgnoreCaseOrCorreoContainingIgnoreCase(keyword, keyword);
        }
        return usuarioRepository.findAll();
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario crearUsuario(Usuario usuario) {
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario datos) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(datos.getNombre());

        if (datos.getContraseña() != null && !datos.getContraseña().isBlank()) {
            usuario.setContraseña(passwordEncoder.encode(datos.getContraseña()));
        }

        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public boolean existeCedula(String cedula) {
        return usuarioRepository.findByUsuario(cedula).isPresent();
    }
}
