package com.example.demo.services;

import com.example.demo.models.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
//Servicio encargado de gestionar quien entra y que puede hacer dentro del sistema
@Service
public class UsuarioDetailsService implements UserDetailsService {

    /*Inyeccion por constructor spring detecta las dependencias y las inserta
    En este caso se dice que esta dependencia es obligatoria y no va cambiar
    conecta la base de datos especificamente el repositorio de usuario con spring security*/
    private final UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    /*
    Escriben en el login el usuario y esto lo busca y va al repositorio a encontrarlo,
    si no lo encuentra lanza la excepcion
    */
    @Override//sobreescritura personalizada del metodo de UserDetailsService
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        System.out.println("ENTRANDO AL SERVICE");
        Usuario entidadusuario = usuarioRepository.findByUsuario(usuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));


        var authorities = entidadusuario.getRoles().stream()//flujo de datos secuencial de usuarios para procesas uno a uno
                    //Flatmap: aplana todos los datos y los junta. Nombre del rol y todos sus permisos
                .flatMap(rol -> {//por cada rol hay una lista de las autoridades(permisos)
                    var autoridades = new java.util.ArrayList<SimpleGrantedAuthority>();//lista vacia para guardar el rol y los permisos
                    // agregacion del rol (con prefijo ROLE_) y todos sus permisos para que spring lo entienda
                    autoridades.add(new SimpleGrantedAuthority("ROLE_" + rol.getNombre_rol()));
                    rol.getPermisos().forEach(p -> autoridades.add(new SimpleGrantedAuthority(p.getAccion())));
                    return autoridades.stream();//devuelve un flujo de datos por que flatmap trabaja con streams
                })
                .toList();//convierte ese stream en una lista de SimpleGrantedAuthority para comprension de spring
        /* para cada rol del usuario, toma el rol y sus permisos, los convierte en authorities,
        y júnta todos en una sola lista*/
        System.out.println("ROLES: " + entidadusuario.getRoles());
        System.out.println("USUARIO: " + entidadusuario.getUsuario());

        entidadusuario.getRoles().forEach(rol -> {
            System.out.println("UEAPA ROL: " + rol.getNombre_rol());

            rol.getPermisos().forEach(p -> {
                System.out.println("PERMISO: " + p.getAccion());
            });
        });
        System.out.println("ROLES : " + entidadusuario.getRoles().size());

        entidadusuario.getRoles().forEach(rol -> {
            System.out.println("PERMISOS : " + rol.getPermisos().size());
        });
        return User.builder()
                .username(entidadusuario.getUsuario())
                .password(entidadusuario.getContraseña()) // contraseña encriptada
                .authorities(authorities)
                .build();
        /*entonces termina la comprension de los datos a traves del user.builder utilizando el usuario
        que viene del repositorio que se inyecto en el servicio, con la contrasena pasa lo mismo, pero toma
        los roles y autoridades de una lista de tipo
        SimpleGrantedAuthority y finalmente declara la construccion del objeto y lo retorna */
    }
}