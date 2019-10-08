package au.usyd.elec5619.KAF.model;

public class Product {
	private Integer product_id;
	private Integer store_id;
	private Integer brand_id;
	
	
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", store_id=" + store_id + ", brand_id=" + brand_id + "]";
	}
	
	
}