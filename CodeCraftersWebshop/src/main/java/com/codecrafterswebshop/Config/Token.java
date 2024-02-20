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
import java.util.Date;

/**
 *
 * @author tothm23
 */
public class Token {

    private static final String secret = "hDK2u4vlhFjXjxCFPpSWs9jj";

    public static String getSecret() {
        return secret;
    }

    public static String create(User u, long expirationMillis) {

        long nowMillis = System.currentTimeMillis();
        String token = Jwts.builder()
                .setIssuer("codecrafters")
                .claim("id", u.getId())
                .claim("userName", u.getUserName())
                .claim("lastName", u.getLastName())
                .claim("firstName", u.getFirstName())
                .claim("email", u.getEmail())
                .claim("admin", u.getAdmin())
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

    public static int decodeAdmin(String token) {
        try {
            Jws<Claims> result = Jwts.parser().setSigningKey(TextCodec.BASE64.decode(getSecret())).parseClaimsJws(token);
            int admin = result.getBody().get("admin", Integer.class);
            return 1;

        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException ex) {
            return 2;
        }
    }
}
