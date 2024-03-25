package com.codecrafterswebshop.Service;

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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author tothm23
 */
public class TokenService {

    public static final String SECRET = "hDK2u4vlhFjXjxCFPpSWs9jj";

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
                .signWith(SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode(TokenService.SECRET)
                )
                .compact();

        return token;
    }

    public static int decodeAdmin(String token) {
        try {
            Jws<Claims> result = Jwts.parser().setSigningKey(TextCodec.BASE64.decode(TokenService.SECRET)).parseClaimsJws(token);
            int admin = result.getBody().get("admin", Integer.class);
            return admin == 0 ? 0 : -1;

        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException ex) {
            return -1;
        }
    }

    public static int decodeUser(String token) {
        try {
            Jws<Claims> result = Jwts.parser().setSigningKey(TextCodec.BASE64.decode(TokenService.SECRET)).parseClaimsJws(token);
            return result.getBody().get("id", Integer.class);

        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException ex) {
            return -1;
        }
    }

    public static String decodeUserByEmail(String token) {
        try {
            Jws<Claims> result = Jwts.parser().setSigningKey(TextCodec.BASE64.decode(TokenService.SECRET)).parseClaimsJws(token);
            return result.getBody().get("email", String.class);

        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException ex) {
            return "";
        }
    }

    public static Response filterUser(Response userResponse, HttpHeaders headers, Integer id) {

        Response unauthorized = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Hozzáférés megtagadva!")
                .type(MediaType.TEXT_PLAIN).build();

        String authHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized;
        }

        String token = authHeader.substring("Bearer".length()).trim();

        if (TokenService.decodeUser(token) != id) {
            return userResponse;
        }

        return unauthorized;
    }

    public static Response filterAdmin(Response notAdmin, HttpHeaders headers) {

        Response unauthorized = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Hozzáférés megtagadva!")
                .type(MediaType.TEXT_PLAIN).build();

        String authHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized;
        }

        String token = authHeader.substring("Bearer".length()).trim();
        if (TokenService.decodeAdmin(token) == 0) {
            return notAdmin;
        }

        return unauthorized;
    }

    public static Response filterEmail(Response userResponse, HttpHeaders headers, String email) {

        Response unauthorized = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Hozzáférés megtagadva!")
                .type(MediaType.TEXT_PLAIN).build();

        String authHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized;
        }

        String token = authHeader.substring("Bearer".length()).trim();

        if (!TokenService.decodeUserByEmail(token).equals(email)) {
            return userResponse;
        }

        return unauthorized;
    }

    public static int decodeUser(HttpHeaders headers) {

        String authHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return -1;
        }

        String token = authHeader.substring("Bearer".length()).trim();

        return TokenService.decodeUser(token);
    }
}
