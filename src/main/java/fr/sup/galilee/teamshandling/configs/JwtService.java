package fr.sup.galilee.teamshandling.configs;

import fr.sup.galilee.teamshandling.utils.Dates;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JwtService {
    private final UserDetailsService userDetailsService;

    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    public JwtService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String generate(String id, String issuer, String subject, String roles) {
        LocalDateTime issuedAt = LocalDateTime.now();
        return Jwts.builder()
                .id(id)
                .issuer(issuer)
                .subject(subject)
                .issuedAt(Dates.from(issuedAt))
                .expiration(Dates.from(issuedAt.plusDays(1L)))
                .signWith(SECRET_KEY)
                .claim("roles", roles)
                .compact();
    }

    public Claims getClaims(String jwtToken) {
        return (Claims) Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parse(jwtToken.replace("Bearer ", ""))
                .getPayload();
    }

    public void connectUserUsingJwtClaims(Claims claims) {
        assertNotNullClaims(claims);
        assertNotExpiredClaims(claims);
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getIssuer());
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid Token Access");
        }
        UsernamePasswordAuthenticationToken authenticatedToken = UsernamePasswordAuthenticationToken
                .authenticated(userDetails.getUsername(), null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticatedToken);
    }

    private static void assertNotExpiredClaims(Claims claims) {
        if (claims.getExpiration().before(new Date())) {
            throw new BadCredentialsException("Expired Token Access");
        }
    }

    private static void assertNotNullClaims(Claims claims) {
        if (claims == null) {
            throw new BadCredentialsException("Invalid Token Access");
        }
    }
}