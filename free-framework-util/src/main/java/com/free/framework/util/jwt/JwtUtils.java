package com.free.framework.util.jwt;

import com.free.framework.util.jwt.constants.JwtConsts;
import io.jsonwebtoken.*;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类
 * @author lipeng
 */
public class JwtUtils {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("id", "100001");
        map.put("issuser", "天朝");
        map.put("subject", "天朝的臣民");
        map.put("audience", "天朝应用");
        map.put("issuedAt", new Date());
        map.put("expiration", new Date());
        String token = generateToken(map);
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

    /**
     * 生成jwt token
     * @param id                    编号
     * @param issuser               签发者
     * @param subject               面向的用户
     * @param audience              接收方
     * @param issuedAt              签发时间
     * @param expiration            过期时间
     * @param signatureAlgorithm    签名算法
     * @param key                   签名密钥
     * @return
     */
    public static String generateToken(String id, String issuser, String subject,
                                       String audience, Date issuedAt, Date expiration,
                                       SignatureAlgorithm signatureAlgorithm, Key key) {
        String compactJws = Jwts.builder()
                .setId(id)
                .setIssuer(issuser)
                .setSubject(subject)
                .setAudience(audience)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(signatureAlgorithm, key)
                .compact();
        return compactJws;
    }

    /**
     * 生成jwt token
     * @param body  内容可以是如上方法,处理前面方式和签名密钥之外
     * @return
     */
    public static String generateToken(Map<String, Object> body) {
        String compactJws = Jwts.builder()
                .setClaims(body)
                .signWith(JwtConsts.DEFAULT_SIGNATURE_ALGORITHM, JwtConsts.SECRET_KEY)
                .compact();
        return compactJws;
    }

    /**
     * 生成jwt token
     * @param header    header可以自定义参数,默认只有前面算法
     * @param body      内容可以是如上方法,处理前面方式和签名密钥之外
     * @return
     */
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
