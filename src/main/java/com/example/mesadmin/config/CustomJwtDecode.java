package com.example.mesadmin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CustomJwtDecode implements JwtDecoder {

    @Value("${security.jwt.signal-key}")
    private String SIGNER_KEY;

    @Autowired
    private AuthenticationService authService;

    private NimbusJwtDecoder nimbusJwtDecoder = null;

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            var response = authService.introspect(token);
            if (!response.getValid())
                throw new JwtException("Token invalid");
        } catch (JwtException e) {
            throw new JwtException(e.getMessage());
        }

        if (Objects.isNull(nimbusJwtDecoder)){
            SecretKeySpec spec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(spec).macAlgorithm(MacAlgorithm.HS512).build();
        }
        return nimbusJwtDecoder.decode(token);
    }
}
