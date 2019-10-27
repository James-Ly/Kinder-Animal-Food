package au.usyd.elec5619.KAF.user;

import au.usyd.elec5619.KAF.model.Brand;

public class CrmBrandWithRating {
	
	private Brand brand;
	
	private int bestRating;
	private int avoidRating;
	private int goodRating;
	
	public CrmBrandWithRating() {
		
	}

	public CrmBrandWithRating(Brand brand, int bestRating, int avoidRating, int goodRating) {
		super();
		this.brand = brand;
		this.bestRating = bestRating;
		this.avoidRating = avoidRating;
		this.goodRating = goodRating;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public int getBestRating() {
		return bestRating;
	}

	public void setBestRating(int bestRating) {
		this.bestRating = bestRating;
	}

	public int getAvoidRating() {
		return avoidRating;
	}

	public void setAvoidRating(int avoidRating) {
		this.avoidRating = avoidRating;
	}

	public int getGoodRating() {
		return goodRating;
	}

	public void setGoodRating(int goodRating) {
		this.goodRating = goodRating;
	}

	@Override
	public String toString() {
		return "CrmBrandWithRating [brand=" + brand + ", bestRating=" + bestRating + ", avoidRating=" + avoidRating
				+ ", goodRating=" + goodRating + "]";
	}
	
}
