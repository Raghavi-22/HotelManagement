package demo.demo.Repository;


import demo.demo.Module.HotelDepartment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HotelDepartmentRepository  {

    private final JdbcTemplate jdbcTemplate;

    public HotelDepartmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<HotelDepartment> findAll() {
        String sql = "SELECT * FROM HotelDepartment";
        return jdbcTemplate.query(sql, new HotelDepartmentRowMapper());
    }


    public HotelDepartment findBySNO(int sno) {
        String sql = "SELECT * FROM HotelDepartment WHERE SNO = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{sno}, new HotelDepartmentRowMapper());
    }

    public void save(HotelDepartment hotelDepartment) {
        String sql = "INSERT INTO HotelDepartment (Hotelno, Departmentno) VALUES (?, ?)";
        jdbcTemplate.update(sql, hotelDepartment.getHotelno(), hotelDepartment.getDepartmentno());
    }


    public void deleteBySNO(int sno) {
        String sql = "DELETE FROM HotelDepartment WHERE SNO = ?";
        jdbcTemplate.update(sql, sno);
    }

    private static class HotelDepartmentRowMapper implements RowMapper<HotelDepartment> {
        @Override
        public HotelDepartment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new HotelDepartment(
                    rs.getInt("SNO"),
                    rs.getInt("Hotelno"),
                    rs.getInt("Departmentno")
            );
        }
    }
}

