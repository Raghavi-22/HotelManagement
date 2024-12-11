package demo.demo.Services;

import demo.demo.Module.Staff;
import demo.demo.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StaffDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public  UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Staff staff = staffRepository.getStaffByEmail(email);

        // Check if the staff is null and throw the exception if not found
        if (staff == null) {
            throw new UsernameNotFoundException("Staff not found with email: " + email);
        }

        // Convert staff roles to authorities
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_STAFF"));

        return new org.springframework.security.core.userdetails.User(
                staff.getpEmail(),
                staff.getPassword(),
                authorities
        );
    }
}
