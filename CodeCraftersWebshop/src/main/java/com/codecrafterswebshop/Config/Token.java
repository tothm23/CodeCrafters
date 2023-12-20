package com.codecrafterswebshop.Config;

import com.codecrafterswebshop.Model.Felhasznalo;
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

    public static String letrehozas(Felhasznalo f, long expirationMillis) {

        long nowMillis = System.currentTimeMillis();

        String token = Jwts.builder()
                .setIssuer("codecrafters")
                .claim("id", f.getId())
                .claim("felhasznaloNev", f.getFelhasznaloNev())
                .claim("vezetekNev", f.getVezetekNev())
                .claim("keresztNev", f.getKeresztNev())
                .claim("email", f.getEmail())
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(nowMillis + expirationMillis))
                .signWith(
                        SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode("RXogbGVzeiBhIHRpdGtvcyBrdWxjcw==")
                )
                .compact();

        return token;
    }

    public static int dekodolas(String token) {
        try {
            String secret = "RXogbGVzeiBhIHRpdGtvcyBrdWxjcw==";
            Jws<Claims> result = Jwts.parser().setSigningKey(TextCodec.BASE64.decode(secret)).parseClaimsJws(token);
            return 1;

        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException ex) {
            return 2;
        }
    }
}
