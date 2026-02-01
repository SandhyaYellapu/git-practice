package Octopi.example.demo4.Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTUtil
{
    private final String SECRET="a-string-secret-at-least-256-bits-long";
    private final SecretKey key= Keys.hmacShaKeyFor(SECRET.getBytes());
    private final long EXPIRATION_TIME = 1000*60*60;
//  public String generateToken(String username)
//  {
//     return Jwts.builder()
//             .setSubject(username)
//             .setIssuedAt(new Date())
//             .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
//             .signWith(key,SignatureAlgorithm.HS256)
//             .compact();
//  }

    public String generateToken(String username,
                                Collection<? extends GrantedAuthority> authorities) {

        List<String> perms = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(username)
                .claim("authorities", perms)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token)
  {
      return extractClaims(token).getSubject();
  }

    private Claims extractClaims(String token) {
        Claims body=Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return body;
    }

    public List<String> extractAuthorities(String token) {
        return extractClaims(token).get("authorities", List.class);
    }

    public boolean validate(UserDetails userDetails,String username,String token)
    {
        return  username.equals(userDetails.getUsername())&&!isTokenExpired(token);
    }

    private boolean isTokenExpired(String token)
    {
             System.out.println("token expired");
        return  extractClaims(token).getExpiration().before(new Date());
    }
}
