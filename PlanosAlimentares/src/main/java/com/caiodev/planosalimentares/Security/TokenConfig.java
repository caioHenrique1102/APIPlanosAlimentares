package com.caiodev.planosalimentares.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.caiodev.planosalimentares.Model.Entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Optional;

@Component
public class TokenConfig {

    @Value("${security.token.secret}")
    private String secret;
    @Value("${security.refreshToken.expires-ms}")
    private Long tokenRefresh;
    @Value("${security.acessToken.expires-ms}")
    private Long tokenAcess;

    public String RefreshToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("userId", usuario.getId())
                .withSubject(usuario.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(tokenRefresh))
                .withIssuedAt(Instant.now())
                .sign(algorithm);

    }

    public String AcessToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(usuario.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(tokenAcess))
                .withIssuedAt(Instant.now())
                .sign(algorithm);

    }

    public Optional<JWTUserData> validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            DecodedJWT decodedJWT = JWT.require(algorithm)
            .build().verify(token);
            return Optional.of(JWTUserData.builder()
                    .userId(decodedJWT.getClaim("userId").asLong())
                    .email(decodedJWT.getSubject())
                    .build());
        }
        catch (JWTVerificationException e) {
                return Optional.empty();
        }
    }
}
