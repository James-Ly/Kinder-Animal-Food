package au.usyd.elec5619.KAF.model;

public class Accreditation{
	
	private String accreditation_id;
	private String accreditation_name;
	private String rating;
	private String description;
	
	public String getAccreditation_id() {
		return accreditation_id;
	}
	public void setAccreditation_id(String accreditation_id) {
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Accreditation [accreditation_id=" + accreditation_id + ", accreditation_name=" + accreditation_name
				+ ", rating=" + rating + ", description=" + description + "]";
	}
	
	
}