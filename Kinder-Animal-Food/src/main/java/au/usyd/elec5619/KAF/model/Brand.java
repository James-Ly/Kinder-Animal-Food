package au.usyd.elec5619.KAF.model;

public class Brand {
	private Integer brand_id;
	private String brand_name;
	private String brand_category;
	private String accreditation_id;
	private String image;
	
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getBrand_category() {
		return brand_category;
	}
	public void setBrand_category(String brand_category) {
		this.brand_category = brand_category;
	}
	public String getAccreditation_id() {
		return accreditation_id;
	}
	public void setAccreditation_id(String accreditation_id) {
		this.accreditation_id = accreditation_id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Brand [brand_id=" + brand_id + ", brand_name=" + brand_name + ", brand_category=" + brand_category
				+ ", accreditation_id=" + accreditation_id + ", image=" + image + "]";
	}


}