package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.config;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.jwt.JwtFilter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public abstract class WebSecurityConfigAbstract {

    protected final HttpSecurity securityFilterChainPrivate(HttpSecurity httpSecurity,
                                                            JwtFilter jwtFilter,
                                                            AuthenticationEntryPoint exceptionHandling,
                                                            AuthenticationProvider authenticationProvider) throws Exception {
        httpSecurity
                .csrf((AbstractHttpConfigurer::disable))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(authorize -> authorize
                        // Public EndPoints
                        //.requestMatchers(HttpMethod.POST, "/auth/**").permitAll() //Opción 1
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll() //Opción 2

                        // Private EndPoints
                        .requestMatchers(HttpMethod.GET, "/method/get").hasAnyAuthority("READ", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/method/post").hasAuthority("CREATE")
                        //.requestMatchers(HttpMethod.DELETE, "/method/delete").hasAuthority("DELETE") //Porque hay un ROL que tambié apunta l mismo endpoint, estoy verloa al final, cuando tenga el proyecto funcionando correctamente
                        .requestMatchers(HttpMethod.PUT, "/method/put").hasAuthority("UPDATE")

                        // Privileges by Roles
                        .requestMatchers(HttpMethod.DELETE, "/method/delete").hasRole("ADMIN")

                        // Para resto de peticiones 2 alternativas:
                        //.anyRequest().denyAll() //Deniega toda petición así esté o no autenticado el usuario
                        .anyRequest().authenticated()//Cualquier otra petición debe de estar autenticado el usuario
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(handler -> handler.authenticationEntryPoint(exceptionHandling));
        //Ver que pasa si lo quito, no salta error al quitarlo, comprobar su funcionamiento y la diferencia con ponerlo + ver video del patrón build() youtube "Un programador nace"
        return httpSecurity;
    }
}