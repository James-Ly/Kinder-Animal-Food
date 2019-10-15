package au.usyd.elec5619.KAF.model;

import java.util.ArrayList;
import java.util.List;

public class BrandWithAllAccreditations {
	
	private List<Integer> brand_ids = new ArrayList<Integer>();

	private String brand_name;

	private String brand_category;

	private String image;
	
	private List<Accreditation> accreditations = new ArrayList<Accreditation>();

	public List<Integer> getBrand_ids() {
		return brand_ids;
	}

	public void setBrand_ids(Integer brand_id) {
		this.brand_ids.add(brand_id);
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

	public List<Accreditation> getAccreditations() {
		return accreditations;
	}

	public void setAccreditations(List<Accreditation> accreditations) {
		this.accreditations = accreditations;
	}
	
	public void setAccreditation(Accreditation accreditation) {
		this.accreditations.add(accreditation);
	}

	public List<Brand> getBrands() {
		
		List<Brand> brands = new ArrayList<Brand>();
		
		for(int i = 0; i < accreditations.size(); i++) {
			brands.get(i).setBrand_id(this.brand_ids.get(i));
			brands.get(i).setBrand_name(this.brand_name);
			brands.get(i).setBrand_category(this.brand_category);
			brands.get(i).setAccreditation_id(this.accreditations.get(i).getAccreditation_id());
			brands.get(i).setImage(this.image);
		}

		return brands;
	}

	@Override
	public String toString() {
		return "BrandWithAllAccreditations [brand_id=" + brand_ids + ", brand_name=" + brand_name + ", brand_category="
				+ brand_category + ", image=" + image + ", accreditations=" + accreditations + "]";
	}


	
}