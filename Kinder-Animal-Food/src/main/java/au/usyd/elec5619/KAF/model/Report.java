package au.usyd.elec5619.KAF.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Report")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "report_id")
	private Integer report_id;

	@Column(name = "user_id")
	private Integer user_id;

	@Column(name = "report_time")
	private Date report_time;

	@Column(name = "brand_id")
	private Integer brand_id;

	@Column(name = "store_name_by_user")
	private String store_name_by_user;

	@Column(name = "store_address_by_user")
	private String store_address_by_user;

	@Column(name = "store_state_by_user")
	private String store_state_by_user;

	@Column(name = "store_postcode_by_user")
	private String store_postcode_by_user;

	@Column(name = "status")
	private String status;

	public Integer getReport_id() {
		return report_id;
	}

	public void setReport_id(Integer report_id) {
		this.report_id = report_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Date getReport_time() {
		return report_time;
	}

	public void setReport_time(Date report_time) {
		this.report_time = report_time;
	}

	public Integer getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}

	public String getStore_name_by_user() {
		return store_name_by_user;
	}

	public void setStore_name_by_user(String store_name_by_user) {
		this.store_name_by_user = store_name_by_user;
	}

	public String getStore_address_by_user() {
		return store_address_by_user;
	}

	public void setStore_address_by_user(String store_address_by_user) {
		this.store_address_by_user = store_address_by_user;
	}

	public String getStore_state_by_user() {
		return store_state_by_user;
	}

	public void setStore_state_by_user(String store_state_by_user) {
		this.store_state_by_user = store_state_by_user;
	}

	public String getStore_postcode_by_user() {
		return store_postcode_by_user;
	}

	public void setStore_postcode_by_user(String store_postcode_by_user) {
		this.store_postcode_by_user = store_postcode_by_user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Report [report_id=" + report_id + ", user_id=" + user_id + ", report_time=" + report_time
				+ ", store_name_by_user=" + store_name_by_user + ", store_address_by_user=" + store_address_by_user
				+ ", store_state_by_user=" + store_state_by_user + ", store_postcode_by_user=" + store_postcode_by_user
				+ ", status=" + status + "]";
	}

}