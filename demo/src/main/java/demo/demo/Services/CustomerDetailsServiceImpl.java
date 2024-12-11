package demo.demo.Services;


import demo.demo.Module.Staff;
import demo.demo.Module.User;
import demo.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomerDetailsServiceImpl implements UserDetailsService{
    private final UserRepo customerRepository;
@Autowired
    public CustomerDetailsServiceImpl(UserRepo customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Try to find the user by username
        System.out.println(email);
        User user = customerRepository.getUserByEmail(email);

        // Check if the staff is null and throw the exception if not found
        if (user == null) {

            throw new UsernameNotFoundException("person not found with email: " + email);
        }
        // If user is found, convert roles to authorities

        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        System.out.println(authorities);
        System.out.println("ajay+good");

        // Return the Spring Security user with roles and password
        return new org.springframework.security.core.userdetails.User(
                user.getpEmail(),
                user.getPassword(),
                authorities
        );
    }
}