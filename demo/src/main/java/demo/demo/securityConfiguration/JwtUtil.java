package demo.demo.securityConfiguration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "CUSTOMER_LOGIN";

    // Create token with roles
    public String createToken(Authentication authentication,List<String> roles) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("creation token is called");
        System.out.println(userDetails);

        int expirationTimeInMilliSeconds = 3 * 60 * 60 * 1000; // 3 hours

        return Jwts.builder()
                .setSubject(userDetails.getUsername())    // Setting the email/username as subject
                .claim("roles", roles)                     // Adding roles as a custom claim
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expirationTimeInMilliSeconds))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // Sign with the secret key
                .compact();
    }

    // Extract email from token
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Extract roles from token
    public List<String> getRolesFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        // Return roles as a list of strings
        return (List<String>) claims.get("roles");
    }

    // Check if the token has a specific role
    public boolean hasRole(String token, String role) {
        List<String> roles = getRolesFromToken(token);
        return roles.contains(role);
    }

    // Validate the token
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            System.out.println("token validated");
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
