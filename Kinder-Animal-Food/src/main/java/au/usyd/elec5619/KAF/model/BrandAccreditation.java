package au.usyd.elec5619.KAF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BrandAccreditation")
public class BrandAccreditation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "brandAccreditation_id")
	private Integer brandAccreditation_id;
	
	@Column(name = "brand_id")
	private Integer brand_id;
	
	@Column(name = "accreditation_id")
	private Integer accreditation_id;

	public Integer getBrandAccreditation_id() {
		return brandAccreditation_id;
	}

	public void setBrandAccreditation_id(Integer brandAccreditation_id) {
		this.brandAccreditation_id = brandAccreditation_id;
	}

	public Integer getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}

	public Integer getAccreditation_id() {
		return accreditation_id;
	}

	public void setAccreditation_id(Integer accreditation_id) {
		this.accreditation_id = accreditation_id;
	}

	@Override
	public String toString() {
		return "BrandAccreditation [brandAccreditation_id=" + brandAccreditation_id + ", brand_id=" + brand_id
				+ ", accreditation_id=" + accreditation_id + "]";
	}
	
}