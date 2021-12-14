package miu.edu.com.courseregistrationsystem.util;

import io.jsonwebtoken.*;
import miu.edu.com.courseregistrationsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

//@Service
public class JwtUtil {

  //  @Autowired AccountService accountService;

    private String secret;
    private int jwtExpirationInMs;
    private int refreshExpirationDateInMs;

    @Value("${jwt.secret}")
    public void setSecret(String secret){
        this.secret = secret;
    }

    @Value("${jwt.jwtExpirationInMs}")
    public void setJwtExpirationInMs(int jwtExpirationInMs) {
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    @Value("${jwt.refreshExpirationDateInMs}")
    public void setRefreshExpirationDateInMs(int refreshExpirationDateInMs) {
        this.refreshExpirationDateInMs = refreshExpirationDateInMs;
    }

    public String generateToken(UserDetails userDetails){

        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

        if(roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            claims.put("role", "ROLE_ADMIN");
        }
        if(roles.contains(new SimpleGrantedAuthority("ROLE_STUDENT"))){
            claims.put("role", "ROLE_STUDENT");
        }

        //claims.put("userId",accountService.getAccountByUsername(userDetails.getUsername()).getId());
        return doGenerateToken(claims, userDetails.getUsername());

    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().
                setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationDateInMs))
                .signWith(SignatureAlgorithm.HS512, secret).compact();

    }

    public boolean validateToken(String authToken){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);

        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex){
            throw new BadCredentialsException("INVALID CREDENTIALS", ex);
        } catch (ExpiredJwtException ex){
//            TODO
//            throw new Exception(header, claims, "Token has expired", ex);

        }
        return true;

//        final String username = getUsernameFromToken(authToken);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


    public List<SimpleGrantedAuthority> getRolesFromToken(String authToken) {
        List<SimpleGrantedAuthority> roles = null;

        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken).getBody();
        Boolean isAdmin = claims.get("isAdmin", Boolean.class);
        Boolean isStudent = claims.get("isStudent", Boolean.class);


        if (isAdmin != null && isAdmin == true) {
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if (isStudent != null && isStudent == true) {
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_STUDENT"));
        }

        return roles;
    }
}
