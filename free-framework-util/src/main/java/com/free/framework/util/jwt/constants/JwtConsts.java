package com.free.framework.util.jwt.constants;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;

/**
 * jwt常量
 * @author lipeng
 */
public interface JwtConsts {

    /**
     * 加密使用的私钥
     */
    Key SECRET_KEY = MacProvider.generateKey();

    /**
     * 加密算法
     */
    SignatureAlgorithm DEFAULT_SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    /**
     * 默认的签发时间
     */
    Date DEFAULT_ISSUED_At = new Date(System.currentTimeMillis());

    /**
     *  默认时长30天
     */
    Long DEFAULT_TIME = 30L * 24 * 60 * 60 * 1000;

    /**
     * 默认失效时间
     */
   Date DEFAULT_EXPIRATION = new Date(System.currentTimeMillis() + DEFAULT_TIME);

}
