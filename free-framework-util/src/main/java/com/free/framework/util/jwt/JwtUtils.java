package com.free.framework.util.jwt;

import com.free.framework.util.jwt.constants.JwtConsts;
import io.jsonwebtoken.*;

import java.util.Map;

/**
 * jwt工具类
 * @author lipeng
 */
public class JwtUtils {

    public static void main(String[] args) {
        String token = generateToken();
        System.out.println(validateToken(token));
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static boolean validateToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(JwtConsts.SECRET_KEY).parseClaimsJws(token);
        JwsHeader header = claimsJws.getHeader();
        Claims claims = claimsJws.getBody();
        String algorithm = header.getAlgorithm();
        String validateToken = Jwts.builder()
                .signWith(SignatureAlgorithm.forName(algorithm), JwtConsts.SECRET_KEY)
                .setHeader((Map<String, Object>) header)
                .setClaims(claims)
                .compact();
        return validateToken.equals(token);
    }

    public static String generateToken() {
        String compactJws = Jwts.builder()
                .setId("1")
                .setIssuer("签发者")
                .setSubject("面向的用户")
                .setAudience("接收方")
                .setIssuedAt(JwtConsts.DEFAULT_ISSUED_At)
                .setExpiration(JwtConsts.DEFAULT_EXPIRATION)
                .signWith(JwtConsts.DEFAULT_SIGNATURE_ALGORITHM, JwtConsts.SECRET_KEY)
                .compact();
        return compactJws;
    }

    public static String generateToken(Map<String, Object> body) {
        String compactJws = Jwts.builder()
                .setClaims(body)
                .signWith(JwtConsts.DEFAULT_SIGNATURE_ALGORITHM, JwtConsts.SECRET_KEY)
                .compact();
        return compactJws;
    }

    public static String generateToken(Map<String, Object> header, Map<String, Object> body) {
        SignatureAlgorithm signatureAlgorithm = (SignatureAlgorithm) header.get(JwsHeader.ALGORITHM);
        String compactJws = Jwts.builder()
                .setHeader(header)
                .setClaims(body)
                .signWith(signatureAlgorithm, JwtConsts.SECRET_KEY)
                .compact();
        return compactJws;
    }
}
