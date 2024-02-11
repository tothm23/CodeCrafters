package com.codecrafterswebshop.Config;

import com.codecrafterswebshop.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.TextCodec;
import java.util.Base64;
import java.util.Date;

/**
 *
 * @author tothm23
 */
public class Token {

    private static final String secret = generateSecret(24);

    public static String getSecret() {
        return secret;
    }

    private static String generateSecret(int keyLength) {

        int min = 33;
        int max = 126;

        StringBuilder key = new StringBuilder();

        for (int i = 0; i < keyLength; i++) {
            key.append((char) Math.floor(Math.random() * (max - min + 1) + min));
        }

        return Base64.getEncoder().encodeToString(new String(key).getBytes());
    }

    public static String create(User f, long expirationMillis) {

        long nowMillis = System.currentTimeMillis();

        String token = Jwts.builder()
                .setIssuer("codecrafters")
                .claim("id", f.getId())
                .claim("userName", f.getUserName())
                .claim("lastName", f.getLastName())
                .claim("firstName", f.getFirstName())
                .claim("email", f.getEmail())
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(nowMillis + expirationMillis))
                .signWith(
                        SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode(getSecret())
                )
                .compact();

        return token;
    }

    public static int decode(String token) {
        try {
            Jws<Claims> result = Jwts.parser().setSigningKey(TextCodec.BASE64.decode(getSecret())).parseClaimsJws(token);
            return 1;

        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException ex) {
            return 2;
        }
    }
}
