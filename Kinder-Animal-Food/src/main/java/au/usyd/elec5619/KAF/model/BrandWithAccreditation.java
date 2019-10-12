package au.usyd.elec5619.KAF.model;

public class BrandWithAccreditation {
	
	private Integer brand_id;

	private String brand_name;

	private String brand_category;

	private String image;

	private Accreditation accreditation;

	
	
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Accreditation getAccreditation() {
		return accreditation;
	}

	public void setAccreditation(Accreditation accreditation) {
		this.accreditation = accreditation;
	}

	public Brand getBrand() {
		Brand brand = new Brand();
		brand.setBrand_id(this.brand_id);
		brand.setBrand_name(this.brand_name);
		brand.setBrand_category(this.brand_category);
		brand.setAccreditation_id(this.accreditation.getAccreditation_id());
		brand.setImage(this.image);
		return brand;
	}

	@Override
	public String toString() {
		return "BrandWithAccreditation [brand_id=" + brand_id + ", brand_name=" + brand_name + ", brand_category="
				+ brand_category + ", image=" + image + ", accreditation=" + accreditation + "]";
	}


}