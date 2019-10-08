package au.usyd.elec5619.KAF.model;


public class Store {

	private Integer store_id;
	private String store_name;
	private String store_address;
	private String store_state;
	private String store_longitude;
	private String store_latitude;
	private Integer brands_num;
	
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_address() {
		return store_address;
	}
	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}
	public String getStore_state() {
		return store_state;
	}
	public void setStore_state(String store_state) {
		this.store_state = store_state;
	}
	public String getStore_longitude() {
		return store_longitude;
	}
	public void setStore_longitude(String store_longitude) {
		this.store_longitude = store_longitude;
	}
	public String getStore_latitude() {
		return store_latitude;
	}
	public void setStore_latitude(String store_latitude) {
		this.store_latitude = store_latitude;
	}
	public Integer getBrands_num() {
		return brands_num;
	}
	public void setBrands_num(Integer brands_num) {
		this.brands_num = brands_num;
	}
	@Override
	public String toString() {
		return "Store [store_id=" + store_id + ", store_name=" + store_name + ", store_address=" + store_address
				+ ", store_state=" + store_state + ", store_longitude=" + store_longitude + ", store_latitude="
				+ store_latitude + ", brands_num=" + brands_num + "]" + "\n";
	}
	
}
