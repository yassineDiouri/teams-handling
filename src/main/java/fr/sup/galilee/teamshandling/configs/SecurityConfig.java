package fr.sup.galilee.teamshandling.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //.addFilterAfter(jwtValidatorFilter, UsernamePasswordAuthenticationFilter.class)
                //.authorizeHttpRequests((req) -> req.requestMatchers(HttpMethod.POST, "/login").permitAll())
                .authorizeHttpRequests((req) -> req.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .build();

    }
}
