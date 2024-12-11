package demo.demo.Repository;


import demo.demo.Module.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addDepartment(Department department) {
        String sql = "INSERT INTO Department (DeptID, HouseIn, DeptName, Description) VALUES (?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, department.getDeptID(), department.getHouseIn(), department.getDeptName(), department.getDescription());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public Department getDepartmentByID(int deptID) {
        String sql = "SELECT * FROM Department WHERE DeptID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Department.class), deptID);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Department> getAllDepartments() {
        String sql = "SELECT * FROM Department";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Department.class));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int updateDepartment(Department department) {
        String sql = "UPDATE Department SET HouseIn = ?, DeptName = ?, Description = ? WHERE DeptID = ?";
        try {
            return jdbcTemplate.update(sql, department.getHouseIn(), department.getDeptName(), department.getDescription(), department.getDeptID());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int deleteDepartment(int deptID) {
        String sql = "DELETE FROM Department WHERE DeptID = ?";
        try {
            return jdbcTemplate.update(sql, deptID);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
