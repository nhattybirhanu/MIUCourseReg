package miu.edu.com.courseregistrationsystem.Security;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JWTUtil {
    String SECRET_KEY="secret";
    String AUTHORITIES ="AUTHORITIES";
    public String extractUsername(String token){

        return extractClaim(token,Claims::getSubject);
    }
    public String extractRole(String token){
        final JwtParser jwtParser= Jwts.parser().setSigningKey(SECRET_KEY);
        final Jws<Claims> claimsJws=jwtParser.parseClaimsJws(token);
        final Claims claims=claimsJws.getBody();
        return claims.get("AUTHORITIES").toString();
    }

    public Date extractExpiration(String token){

        return extractClaim(token,Claims::getExpiration);
    }


    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){

        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){

        return extractExpiration(token).before(new Date());
    }
    public String generateToken(UserDetails userDetails){
        String claims=userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        System.out.println(claims);
        return createToken(claims,userDetails.getUsername());
    }
    public String createToken(String claims,String subject){
        return Jwts.builder().claim(AUTHORITIES,claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+5000*60*15))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact()
                ;
    }

    public Boolean validToken(String token,UserDetails userDetails){

        return extractUsername(token).equals(userDetails.getUsername())&&!isTokenExpired(token);
    }

    public UsernamePasswordAuthenticationToken authenticationToken(final  String token, UserDetails userDetails){
        final JwtParser jwtParser= Jwts.parser().setSigningKey(SECRET_KEY);
        final Jws<Claims> claimsJws=jwtParser.parseClaimsJws(token);
        final Claims claims=claimsJws.getBody();
        System.out.println(claims);
        Collection<? extends GrantedAuthority> auth= Arrays.stream(claims.get(AUTHORITIES).toString().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList()); ;

        return new UsernamePasswordAuthenticationToken(userDetails,"",auth);
    }

}
