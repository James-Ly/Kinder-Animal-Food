package au.usyd.elec5619.KAF.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import au.usyd.elec5619.domain.Brand;

@Service
public class BrandDaoImpl implements BrandDao {

	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Brand> searchBrandByName(String brand_name) {

		String sql = "select * from Brand where brand_name='" + brand_name + "'";

		List<Brand> brands = jdbcTemplate.query(sql, new BrandMapper());

		return brands.size() > 0 ? brands : null;
	}

	@Override
	public List<Brand> searchBrand(Brand brand) {

		String sql = "SELECT * FROM Brand WHERE brand_name = '" + brand.getBrand_name() + "'"
				+ " AND brand_category = '" + brand.getBrand_category() + "'" + " AND image = '" + brand.getImage()
				+ "'";

		List<Brand> brands = jdbcTemplate.query(sql, new BrandMapper());

		return brands.size() > 0 ? brands : null;
	}

	@Override
	public void insertBrand(Brand brand) {

		jdbcTemplate.update("INSERT INTO Brand VALUES (Null, ?, ?, ?);", brand.getBrand_name(),
				brand.getBrand_category(), brand.getImage());
	}

	@Override
	public void deleteBrand(Brand brand) {

		jdbcTemplate.update("delete from Brand where Brand_id=? ", brand.getBrand_id());
	}

	@Override
	public void editBrand(Brand brand) {

		jdbcTemplate.update("UPDATE Brand SET brand_name = ?, brand_category = ?, image = ? WHERE brand_id = ?;",
				brand.getBrand_name(), brand.getBrand_category(), brand.getImage(), brand.getBrand_id());
	}

	public Integer countBrand() {

		String sql = "select count(*) from Brand";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);

		return count;
	}

}

class BrandMapper implements RowMapper<Brand> {

	public Brand mapRow(ResultSet rs, int arg1) throws SQLException {
		Brand brand = new Brand();

		brand.setBrand_id(rs.getInt("brand_id"));
		brand.setBrand_name(rs.getString("brand_name"));
		brand.setBrand_category(rs.getString("brand_category"));
		brand.setImage(rs.getString("image"));

		return brand;
	}
}
