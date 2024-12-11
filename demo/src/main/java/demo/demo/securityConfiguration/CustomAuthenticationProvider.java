package demo.demo.securityConfiguration;

import demo.demo.Services.AdminDetailsServiceImpl;
import demo.demo.Services.CustomerDetailsServiceImpl;
import demo.demo.Services.StaffDetailsServiceImpl;
import demo.demo.securityConfiguration.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.List;
import java.util.stream.Collectors;


public class CustomAuthenticationProvider implements AuthenticationProvider {


    private final CustomerDetailsServiceImpl customerDetailsService;

    private final StaffDetailsServiceImpl staffDetailsService;

    private final AdminDetailsServiceImpl adminDetailsService;

    private final JwtUtil jwtUtil;

    private HttpServletRequest request; // Inject HttpServletRequest to capture request URL

    public CustomAuthenticationProvider(CustomerDetailsServiceImpl customerDetailsService,
                                        StaffDetailsServiceImpl staffDetailsService,
                                        AdminDetailsServiceImpl adminDetailsService,
                                        JwtUtil jwtUtil
                                      ) {
        this.customerDetailsService = customerDetailsService;
        this.staffDetailsService = staffDetailsService;
        this.adminDetailsService = adminDetailsService;
        this.jwtUtil = jwtUtil;

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = null;

        // Determine which login page is being accessed by checking the URL
        // Get the current HttpServletRequest using RequestContextHolder
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestUrl = request.getRequestURI();
         System.out.println(requestUrl);
        System.out.println("ajay");
        if (isAdminLogin(requestUrl)) {
            // Load admin details
            userDetails = adminDetailsService.loadUserByUsername(email);
        } else if (isStaffLogin(requestUrl)) {
            // Load staff details
            userDetails = staffDetailsService.loadUserByUsername(email);
        } else {
            // Load customer/user details
            userDetails = customerDetailsService.loadUserByUsername(email);
        }

        // Validate credentials and authenticate
        if (userDetails != null && passwordEncoder().matches(password, userDetails.getPassword())) {
            // Successful authentication, create JWT token
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .collect(Collectors.toList());

            String token = jwtUtil.createToken(authentication, roles);  // Create JWT token
            return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Authentication failed: Invalid username or password");
        }
    }

    // Check if the request URL matches for admin login
    private boolean isAdminLogin(String requestUrl) {
        return requestUrl != null && requestUrl.contains("/admin_login");
    }

    // Check if the request URL matches for staff login
    private boolean isStaffLogin(String requestUrl) {
        return requestUrl != null && requestUrl.contains("/staff-login");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
