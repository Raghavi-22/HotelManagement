package demo.demo.Repository;

import demo.demo.Module.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepo {
    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public UserRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addUser(User user) {
        String sql = "INSERT INTO User (UserID, Fname, Mname, Lname, FlatNo, Sex, DOB, City, State, PIN, PhoneNo, Score, pEmail, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, user.getUserID().toString(), user.getFname(), user.getMname(), user.getLname(), user.getFlatNo(), String.valueOf(user.getSex()), user.getDob(), user.getCity(), user.getState(), user.getPin(), user.getPhoneNo(), user.getScore(), user.getpEmail(), user.getPassword());
        } catch (Exception e) {
            System.out.println("Error adding user: " + e.getMessage());
            return 0;
        }


    }

    public User getUserByID(UUID userID) {
        String sql = "SELECT * FROM User WHERE UserID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userID.toString());
        } catch (Exception e) {
            System.out.println("Error fetching user by ID: " + e.getMessage());
            return null;
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM User";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            System.out.println("Error fetching all users: " + e.getMessage());
            return null;
        }
    }

    public int updateUser(User user) {
        String sql = "UPDATE User SET Fname = ?, Mname = ?, Lname = ?, FlatNo = ?, Sex = ?, DOB = ?, City = ?, State = ?, PIN = ?, PhoneNo = ?, Score = ?, pEmail = ? WHERE UserID = ?";
        try {
            return jdbcTemplate.update(sql, user.getFname(), user.getMname(), user.getLname(), user.getFlatNo(), user.getSex(), user.getDob(), user.getCity(), user.getState(), user.getPin(), user.getPhoneNo(), user.getScore(), user.getpEmail(), user.getUserID().toString());
        } catch (Exception e) {
            System.out.println("Error updating user: " + e.getMessage());
            return 0;
        }
    }

    public int deleteUser(UUID userID) {
        String sql = "DELETE FROM User WHERE UserID = ?";
        try {
            return jdbcTemplate.update(sql, userID.toString());
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return 0;
        }
    }
    public int deleteUserByEmail(String pEmail) {
        String sql = "DELETE FROM User WHERE pEmail = ?";
        try {
            return jdbcTemplate.update(sql, pEmail);
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return 0;
        }
    }

    public User getUserByEmail(String pEmail) {
//            System.out.println(pEmail);
        String sql = "SELECT * FROM User WHERE pEmail = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), pEmail);
        } catch (Exception e) {
            System.out.println("Error fetching user by email: " + e.getMessage());
            return null;
        }
    }

    public int registerCustomer(UUID userID, String fname, String mname, String lname, String flatNo, String sex, Date dob, String city, String state, String pin, String phoneNo, int score, String password, String pEmail) {
        String sql = "INSERT INTO User(UserID, Fname, Mname, Lname, FlatNo, Sex, DOB, City, State, PIN, PhoneNo, Score, password, pEmail) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            return jdbcTemplate.update(sql, userID.toString(), fname, mname, lname, flatNo, sex, dob, city, state, pin, phoneNo, score, password, pEmail);
        } catch (Exception e) {
            System.out.println("Error registering customer: " + e.getMessage());
            return 0;
        }
    }

    public boolean customerExist(String pEmail) {
        String sql = "SELECT * FROM User WHERE pEmail = ?";
        try {
            User customer = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), pEmail);
            return customer != null;
        } catch (Exception e) {
            System.out.println("Error checking if customer exists: " + e.getMessage());
            return false;
        }
    }
    public int updateCustomerAttribute(UUID userID, String attributeName, String attributeValue) {
        String sql = "UPDATE User SET " + attributeName + " = ? WHERE userID = ?";
        return jdbcTemplate.update(sql, attributeValue, userID.toString());
    }
    public int updateScoreAttribute(UUID userID, String attributeName, int val) {
        String sql = "UPDATE User SET " + attributeName + " = ? WHERE userID = ?";
        return jdbcTemplate.update(sql, val, userID.toString());
    }

}