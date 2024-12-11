package demo.demo.securityConfiguration;

import demo.demo.Services.AdminDetailsServiceImpl;
import demo.demo.Services.CustomerDetailsServiceImpl;
import demo.demo.Services.StaffDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtService;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // Bypass JWT filter for static resources
        if (requestURI.startsWith("/css") || requestURI.startsWith("/js") || requestURI.startsWith("/images")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        String role = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.getEmailFromToken(token);
            role = String.valueOf(jwtService.getRolesFromToken(token));
            System.out.println("Extracted role from JWT: " + role);
        }

        System.out.println("JWT filter triggered for username: " + username + ", role: " + role);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = loadUserDetailsByRole(role, username);

            if (userDetails != null && jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private UserDetails loadUserDetailsByRole(String role, String username) {
        if (role == null) {
            System.out.println("No role found in token, skipping user loading.");
            return null;
        }

        switch (role) {
            case "[ROLE_USER]":
                return context.getBean(CustomerDetailsServiceImpl.class).loadUserByUsername(username);
            case "[ROLE_STAFF]":
                return context.getBean(StaffDetailsServiceImpl.class).loadUserByUsername(username);
            case "[ROLE_ADMIN]":
                return context.getBean(AdminDetailsServiceImpl.class).loadUserByUsername(username);
            default:
                System.out.println("Invalid role in token: " + role);
                return null;
        }
    }
}
