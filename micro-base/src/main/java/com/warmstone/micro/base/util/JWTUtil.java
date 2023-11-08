package com.warmstone.micro.base.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.warmstone.micro.base.constant.AppConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author warmstone
 * @date 2023/11/8 20:28
 * @description
 */
public class JWTUtil {

    private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    private static final Clock clock = DefaultClock.INSTANCE;

    public static String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public static Date getIssueAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public static Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private static  <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private static Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(AppConstant.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    private static Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordRest) {
        return (lastPasswordRest != null && created.before(lastPasswordRest));
    }

    private static Boolean ignoreTokenExpiration(String token) {
        return false;
    }

    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username, AppConstant.JWT_SECRET);
    }

    private static String doGenerateToken(Map<String, Object> claims, String subject, String secret) {
        final Date createDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getIssueAtDateFromToken(token);
        // token过期时间为30分钟，在token失效前450s内有操作即可刷新token
        return !isCreatedBeforeLastPasswordReset(DateUtil.offsetSecond(created, (int) (AppConstant.JWT_EXPIRATION >> 2)), lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public static String refreshToken(String token) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, AppConstant.JWT_SECRET)
                .compact();
    }

    public static Boolean validateToken(String token, String username) {
        return (StrUtil.equals(username, getUsernameFromToken(token)) && !isTokenExpired(token));
    }

    private static Date calculateExpirationDate(Date createDate) {
        return new Date(createDate.getTime() + AppConstant.JWT_EXPIRATION * 1000);
    }

    public static void main(String[] args) {
        String token = JWTUtil.generateToken("root");
        while (true) {
            logger.info("token: {}", token);
            logger.info("expirationDate: {}", DateUtil.format(JWTUtil.getExpirationDateFromToken(token), DatePattern.NORM_DATETIME_FORMAT));
            logger.info("isTokenExpired: {}", JWTUtil.isTokenExpired(token));
            Boolean canTokenBeRefreshed = JWTUtil.canTokenBeRefreshed(token, DateUtil.date());
            logger.info("canTokenBeRefreshed: {}", canTokenBeRefreshed);
            if (canTokenBeRefreshed) {
                break;
            }
        }
    }
}
