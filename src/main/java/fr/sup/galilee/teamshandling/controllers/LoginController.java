package fr.sup.galilee.teamshandling.controllers;

import fr.sup.galilee.teamshandling.configs.JwtService;
import fr.sup.galilee.teamshandling.dtos.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @GetMapping
    public Authentication login(Authentication authentication) {
        return authentication;
    }

    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken unauthenticated = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
        Authentication authenticate = authenticationManager.authenticate(unauthenticated);
        if (authenticate.isAuthenticated()) {
            String authorities = authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
            return jwtService.generate("ID", loginRequest.username(), "Subject", authorities);
        }
        throw new BadCredentialsException("");
    }

}