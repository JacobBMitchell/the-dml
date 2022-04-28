package dml.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JwtConverter {

    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String getTokenFromUser (User toConvert) {
        String commaSep = "";
        for(GrantedAuthority item :toConvert.getAuthorities()){
            commaSep += item + ",";
        }
        commaSep = commaSep.substring(0, commaSep.length()-1);

        return Jwts.builder().setIssuer("dml-app")
                .setSubject(toConvert.getUsername())
                .claim("authorities",commaSep)
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*360))
                .signWith( key )
                .compact();
    }

    public User getUserFromToken( String token ) {
        try{
            JwtParser parser = Jwts.parserBuilder().requireIssuer("dml-app")
                    .setSigningKey( key )
                    .build();
            Jws<Claims> claims = parser.parseClaimsJws(token.substring(7));
            String username = claims.getBody().getSubject();
            String authorities = (String) claims.getBody().get("authorities");
            String[] authSplit = authorities.split(",");
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (String auth: authSplit){
                grantedAuthorities.add(new SimpleGrantedAuthority(auth));
            }
            return new User(username,username, grantedAuthorities);
        }catch (JwtException ex){
            ex.printStackTrace(System.err);
        }
        return null;
    }
}
