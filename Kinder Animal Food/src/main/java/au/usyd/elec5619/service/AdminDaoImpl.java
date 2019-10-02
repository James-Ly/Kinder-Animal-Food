package au.usyd.elec5619.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import au.usyd.elec5619.domain.AdminLogin;
import au.usyd.elec5619.domain.Admin;

/**
 * Validate the admin by the result in the database.
 * 
 * @author Jeffery
 *
 */
public class AdminDaoImpl implements AdminDao {

	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Admin validateAdmin(AdminLogin login) {

		String sql = "select * from Admin where admin_id='" + login.getUsername() + "' and admin_password='"
				+ login.getPassword() + "'";

		List<Admin> admins = jdbcTemplate.query(sql, new AdminMapper());

		return admins.size() > 0 ? admins.get(0) : null;
	}

}

class AdminMapper implements RowMapper<Admin> {

	public Admin mapRow(ResultSet rs, int arg1) throws SQLException {
		Admin admin = new Admin();

		admin.setId(rs.getString("admin_id"));
		admin.setPassword(rs.getString("admin_password"));

		return admin;
	}
}