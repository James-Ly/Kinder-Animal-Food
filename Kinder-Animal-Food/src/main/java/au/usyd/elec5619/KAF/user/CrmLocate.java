package au.usyd.elec5619.KAF.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CrmLocate {
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String brandName;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String location;
	
	public CrmLocate() {
		
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	

}
