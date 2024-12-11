package demo.demo.controller;

import demo.demo.Module.Booking;
import demo.demo.Module.User;
import demo.demo.Module.Staff;
import demo.demo.Module.RoomType;
import demo.demo.Module.Room;
import demo.demo.Services.AdminService;
import demo.demo.Services.AuthenticationService;
import demo.demo.Services.DashboardService;
import demo.demo.jsonResponse.GetBooking;
import demo.demo.jsonResponse.Rooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class AdminController {

    private final AdminService adminService;
    private final AuthenticationService authenticationService;
    private final DashboardService dashboardService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(AdminService adminService, AuthenticationService authenticationService, DashboardService dashboardService, PasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.authenticationService = authenticationService;
        this.dashboardService = dashboardService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/admin/addEmployee")
    public String addEmployee(@RequestBody Staff userEmployee) {
        UUID empID = UUID.randomUUID();
        String encodedPassword = passwordEncoder.encode(userEmployee.getPassword());
        userEmployee.setPassword(encodedPassword);
        Staff employee = new Staff(
                empID,                                     // StaffID (UUID)
                userEmployee.getBelongsto(),               // Department or Belongsto (foreign key)
                userEmployee.getFirstName(),                   // FirstName
                userEmployee.getLastName(),                   // LastName
                userEmployee.getSalary(),                  // Salary
                userEmployee.getPosition(),                // Position
                userEmployee.getSex(),                  // Sex
                userEmployee.getCity(),                    // City
                userEmployee.getState(),                   // State
                userEmployee.getPin(),                 // PIN
                userEmployee.getAadhaarNo(),               // AadhaarNo
                userEmployee.getAccountNo(),               // AccountNo
                userEmployee.getIfsccode(),                // IFSCCode
                userEmployee.getBankName(),                // BankName
                userEmployee.getpEmail(),                  // Personal Email (PEmail)
                userEmployee.getPassword()                 // Password
        );
        System.out.println(userEmployee.getpEmail());

        adminService.addEmployee(employee);
        System.out.println(userEmployee.getpEmail());

        return "Added Employee";
    }
    @GetMapping("/admin/getAllEmployees")
    public List<Staff> getAllEmployees(){
        List<Staff> u = adminService.getEmployees();
//        System.out.println(u.get(0).getSalary());

        return u;
    }

    @PostMapping("/admin/getEmpByEmpID")
    public String getEmpByEmpID(@RequestBody Staff staff) {
        Staff e = adminService.getEmployeeByEmpID(staff.getStaffID());
        if (e != null) {
            return e.getpEmail();
        } else {
            return "Employee not found";
        }
    }

    @PostMapping("/admin/deleteEmployee")
    public String deleteEmployee(@RequestBody Staff staff) {
        String email = staff.getpEmail();
        System.out.println(email);

        if (adminService.employeeExists(email)) {
            adminService.removeEmployee(email);
            return "Deleted employee with Email: " + email;
        } else {
            return "No employee found with Email: " + email;
        }
    }


    @PostMapping("/admin/deleteCustomer")
    public String deleteCustomer(@RequestBody User user) {
        String pEmail = user.getpEmail();
        adminService.removeCustomer(pEmail);
        return "Deleted customer with Email: " + pEmail;
    }

    @GetMapping("/admin/allCustomers")
    public List<User> allCustomers() {
        System.out.println("JI");
        return adminService.getCustomers();
    }

    @GetMapping("/admin/getAllBookings")
    public ResponseEntity<List<GetBooking>> getAllBookings() {
        try {
            List<GetBooking> bookings = adminService.getBookings();
            if (bookings.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content if no bookings are found
            }
            return ResponseEntity.ok(bookings); // 200 OK with the list of bookings
        } catch (Exception e) {
            // Log the error (optional, depending on your logging setup)
            return ResponseEntity.status(500).body(null); // 500 Internal Server Error for any exception
        }
    }
    @GetMapping("/admin/getRoomTypes")
    public List<RoomType> allRoomTypes() {
        return adminService.getAllRoomTypes();
    }

    @GetMapping("/admin/getAllRooms")
    public List<Rooms> allRooms() {
        return adminService.getAllRooms();
    }
}