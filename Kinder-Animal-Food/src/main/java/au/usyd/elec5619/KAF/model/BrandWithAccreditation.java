package au.usyd.elec5619.KAF.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.usyd.elec5619.KAF.service.AccreditationService;

@Repository
public class BrandWithAccreditation {

	@Autowired
	AccreditationService accreditationService;
	
	private Integer brand_id;

	private String brand_name;

	private String brand_category;

	private Integer accreditation_id;

	private String image;

	private String accreditation_name;

	private String rating;


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

	public Integer getAccreditation_id() {
		return accreditation_id;
	}

	public void setAccreditation_id(Integer accreditation_id) {
		this.accreditation_id = accreditation_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAccreditation_name() {
		return accreditation_name;
	}
	
	public void setAccreditation_name(String accreditation_name) {
		this.accreditation_name = accreditation_name;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public Brand getBrand() {
		Brand brand = new Brand();
		brand.setBrand_id(this.brand_id);
		brand.setBrand_name(this.brand_name);
		brand.setBrand_category(this.brand_category);
		brand.setAccreditation_id(this.accreditation_id);
		brand.setImage(this.image);
		return brand;
	}

	public void setBrand(Brand brand) {
		this.setBrand_id(brand.getBrand_id());
		this.setBrand_name(brand.getBrand_name());
		this.setBrand_category(brand.getBrand_category());
		this.setAccreditation_id(brand.getAccreditation_id());
		this.setImage(brand.getImage());
	}

	@Override
	public String toString() {
		return "BrandWithAccreditation [brand_id=" + brand_id + ", brand_name=" + brand_name + ", brand_category="
				+ brand_category + ", accreditation_id=" + accreditation_id + ", image=" + image
				+ ", accreditation_name=" + accreditation_name + ", rating=" + rating + "]";
	}
	
}