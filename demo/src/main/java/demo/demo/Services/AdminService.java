package demo.demo.Services;
import demo.demo.Module.*;

import demo.demo.Repository.*;
import demo.demo.jsonResponse.GetBooking;
import demo.demo.jsonResponse.Rooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AdminService {

    private final StaffRepository employeeRepository;
    private final UserRepo customerRepository;
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminService(StaffRepository employeeRepository, UserRepo customerRepository, BookingRepository bookingRepository, RoomRepository roomRepository, RoomTypeRepository roomTypeRepository, JdbcTemplate jdbcTemplate) {
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Admin getAdmin(String emailID) {
        String sql = "SELECT * FROM admin WHERE emailID = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Admin.class), new Object[]{emailID});
    }

    public void removeEmployee(String pEmail) {
        employeeRepository.deleteStaffByEmail(pEmail);
    }

    public void removeCustomer(String pEmail) {customerRepository.deleteUserByEmail(pEmail);}

    public void addEmployee(Staff staff){
        employeeRepository.addStaff(
                staff.getStaffID(),
                staff.getBelongsto(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getSalary(),
                staff.getPosition(),
                staff.getSex(),
                staff.getCity(),
                staff.getState(),
                staff.getPin(),
                staff.getAadhaarNo(),
                staff.getAccountNo(),
                staff.getIfsccode(),
                staff.getBankName(),
                staff.getpEmail(),
                staff.getPassword());
    }

    public List<Staff> getEmployees() {
        return employeeRepository.getAllStaff();
    }

    public List<User> getCustomers() {
        return customerRepository.getAllUsers();
    }

    public Staff getEmployeeByEmpID(UUID empID){
        return employeeRepository.getStaffByID(empID);
    }

   public List<GetBooking> getBookings(){ return bookingRepository.getAllBookings(); }

    public List<RoomType>getAllRoomTypes() {return roomTypeRepository.getAllRoomTypes();}

    public List<Rooms>getAllRooms(){return roomRepository.getAllRooms();}

    public boolean employeeExists(String email) {
        String sql = "SELECT COUNT(*) FROM Staff WHERE pEmail = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        System.out.println(count);
        return count > 0;

    }
}