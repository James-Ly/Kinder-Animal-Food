package au.usyd.elec5619.KAF.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import au.usyd.elec5619.domain.Store;

@Service
public class StoreDaoImpl implements StoreDao {

	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Store> searchStoreByName(String store_name) {

		String sql = "select * from Store where store_name='" + store_name + "'";

		List<Store> stores = jdbcTemplate.query(sql, new StoreMapper());

		return stores.size() > 0 ? stores : null;
	}

	@Override
	public List<Store> searchStore(Store store) {

		String sql = "SELECT * FROM Store WHERE store_name = '" + store.getStore_name() + "'" + " AND store_address = '"
				+ store.getStore_address() + "'" + " AND store_state = '" + store.getStore_state() + "'"
				+ " AND store_longitude = '" + store.getStore_longitude() + "'" + " AND store_latitude = '"
				+ store.getStore_latitude() + "'" + " AND brands_num = '" + store.getBrands_num() + "'";

		List<Store> stores = jdbcTemplate.query(sql, new StoreMapper());

		return stores.size() > 0 ? stores : null;
	}
	
	@Override
	public List<Store> storeList(){
		String sql = "SELECT * FROM Store";
		List<Store> stores = jdbcTemplate.query(sql, new StoreMapper());
		return stores.size() > 0 ? stores : null;
	}

	@Override
	public void insertStore(Store store) {

		jdbcTemplate.update("INSERT INTO Store VALUES (Null, ?, ?, ?, ?, ?, 0);", store.getStore_name(),
				store.getStore_address(), store.getStore_state(), store.getStore_longitude(),
				store.getStore_latitude());
	}

	@Override
	public void deleteStore(Store store) {

		jdbcTemplate.update("delete from Store where Store_id=? ", store.getStore_id());
	}

	@Override
	public void editStore(Store store) {

		jdbcTemplate.update(
				"UPDATE Store SET store_name = ?, store_address = ?, store_state = ?, store_state = ?, store_longitude = ?, store_latitude = ? WHERE store_id = ?;",
				store.getStore_name(), store.getStore_address(), store.getStore_state(), store.getStore_longitude(),
				store.getStore_latitude(), store.getStore_id());
	}

	@Override
	public Integer countStore() {

		String sql = "select count(*) from Store";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);

		return count;
	}

}

class StoreMapper implements RowMapper<Store> {

	public Store mapRow(ResultSet rs, int arg1) throws SQLException {
		Store store = new Store();

		store.setStore_id(rs.getInt("store_id"));
		store.setStore_name(rs.getString("store_name"));
		store.setStore_address(rs.getString("store_address"));
		store.setStore_state(rs.getString("store_state"));
		store.setStore_longitude(rs.getString("store_longitude"));
		store.setStore_latitude(rs.getString("store_latitude"));
		store.setBrands_num(rs.getInt("brands_num"));

		return store;
	}
}
