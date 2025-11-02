package com.caiodev.planosalimentares.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.caiodev.planosalimentares.Model.Entity.Usuario;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenConfig {
    private final String secret = "secret";

    public String generateToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("userId", usuario.getId())
                .withSubject(usuario.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(9000))
                .withIssuedAt(Instant.now())
                .sign(algorithm);

    }
}
