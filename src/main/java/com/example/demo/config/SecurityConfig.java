    package com.example.demo.config;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

    @Configuration
    public class SecurityConfig {

        /*@Autowired//inyeccion de dependencias que inyecta los beans necesarios
        // normalmente spring se dirige a una ruta /home pero le estamos diciendo que usemos lo del archivo custom
        private AuthenticationSuccessHandler successHandler;*/


        //inyeccion por constructor, mejor oociion por seguridad evitando errores, no se pueden cambiar despues
        private  final CustomSuccessHandler successHandler;
        @Autowired
        public SecurityConfig(CustomSuccessHandler successHandler) {
            this.successHandler = successHandler;
        }

        @Bean//objeto java gestionado por spring
        //INICIO DE CADENA DE FILTROS DE SEGURIDAD QUE INTERCEPTA CADA SOLICITUD HTTP
        //HttpSecurity es un builder de configuracion que spring usa para definir la seguridad de la cadena de filtros
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(auth -> auth
                            // Rutas públicas (no necesitan login)
                            .requestMatchers("/", "/consultar", "/login", "/css/**", "/js/**", "/images/**", "/terminos/**").permitAll()

                            // Rutas privadas (según rol) con control de acceso basado en roles
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .requestMatchers("/editor/**").hasRole("EDITOR")
                            .requestMatchers("/supervisor/**").hasRole("SUPERVISOR")

                            //todo lo demás requiere autenticación, pero no se especifica cual rol
                            .anyRequest().authenticated()
                    )
                    .formLogin(form -> form
                            .loginPage("/login")
                            .successHandler(successHandler) //  redirige según rol definidos en la clase customsucceshandler
                            .failureUrl("/login?error=true")
                            .permitAll()
                    )
                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login?logout=true")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")//limpieza de sesion
                            .permitAll()
                    )
                    //manejo de acceso sin los permisos o roles debido a una URL
                    .exceptionHandling(ex -> ex
                            .accessDeniedPage("/403")
                    );

            return http.build();// genera toda la cadena de filtros que se creo anteriormente
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
