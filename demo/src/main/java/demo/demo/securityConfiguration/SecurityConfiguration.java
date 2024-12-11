package demo.demo.securityConfiguration;
import demo.demo.Services.AdminDetailsServiceImpl;
import demo.demo.Services.CustomerDetailsServiceImpl;
import demo.demo.Services.StaffDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private CustomerDetailsServiceImpl customerDetailsService;

    @Autowired
    private StaffDetailsServiceImpl staffDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdminDetailsServiceImpl adminDetailsService;

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfiguration(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/","/feedback/list/**","/admin/checkEmployee","/admin_login","/feedback/images/**","/login","/admin","/staff-login","/adminLoginPage","/staffdashboard","/register","/dashboard","/dashboard/profile","/Hotel1","/Hotel2","/admin/customers","/admin/deleteEmp","/admin/empForm","/admin/empManagement","/admin/roomManagement","/admin/allEmployees","/admin/allBookings","/test-role","/feedback","/feedback_form","/feedbackId","/rooms","/payment","/Bookings","/cancellation","/service","/servicepayment","/feedbackimages","/experiences/1","/experiences/2","/services","/LostAndFound","/LostAndFound_add","/LostAndFoundbyid","/status").permitAll()
                        .requestMatchers("/getbookings","/getservicebookings","/booking/cancel","/dashboard/check","/dashboard/book","/feedback/add","feedbackById","/editFeedback","/discount","/getamount","/dopayment","/editProfile","/changePassword","/getProfile","/doservicepayment","/getAvailableServices/","/checkstatus","/lostAndFound/list/").hasAuthority("ROLE_USER")
                        .requestMatchers("/editStaffProfile","/changeStaffPassword","/getStaffProfile","/lostAndFound/add","/lostAndFound/list","/lostAndFound/listbyid","/lostAndFound/edit").hasAuthority("ROLE_STAFF")
                        .requestMatchers("http://localhost:8080/admin/getAllEmployees","http://localhost:8080/admin/allCustomers","/admin/checkEmployee/**","/admin/deleteEmployee","/admin/addEmployee","/admin/getRoomTypes","admin/getAllRooms","/admin/getAllBookings").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(customAuthenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//                .exceptionHandling(exception -> exception
//                        .authenticationEntryPoint(customAuthenticationEntryPoint())  // Set custom entry point for unauthenticated users
//                        .accessDeniedHandler(accessDeniedHandler())  // Set custom Access Denied handler for authorized users
//                );

        System.out.println("CAMEHERE_SECURITY");
        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
        accessDeniedHandler.setErrorPage("/login");  // Redirect to /login on 403 error
        return accessDeniedHandler;
    }

    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();  // Use our custom entry point for unauthenticated access
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(customerDetailsService, staffDetailsService, adminDetailsService, jwtUtil);
    }
}
