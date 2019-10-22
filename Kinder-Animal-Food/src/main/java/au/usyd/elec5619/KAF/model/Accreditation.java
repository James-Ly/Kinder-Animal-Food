package au.usyd.elec5619.KAF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Accreditation")
public class Accreditation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accreditation_id")
	private Integer accreditation_id;
	
	@Column(name = "accreditation_name")
	private String accreditation_name;
	
	@Column(name = "rating")
	private String rating;

	public Integer getAccreditation_id() {
		return accreditation_id;
	}

	public void setAccreditation_id(Integer accreditation_id) {
		this.accreditation_id = accreditation_id;
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

	@Override
	public String toString() {
		return "Accreditation [accreditation_id=" + accreditation_id + ", accreditation_name=" + accreditation_name
				+ ", rating=" + rating + "]";
	}
	
}