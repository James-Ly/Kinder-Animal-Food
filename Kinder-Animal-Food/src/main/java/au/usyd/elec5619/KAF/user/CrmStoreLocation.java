package au.usyd.elec5619.KAF.user;

import java.util.List;

import au.usyd.elec5619.KAF.model.Store;

public class CrmStoreLocation {
	
	List<Store> storeList;
	private String lat;
	private String lng;
	private int radius;
	
	public CrmStoreLocation() {
		
	}
	
	public CrmStoreLocation(List<Store> storeList, String lat, String lng, int radius) {
		super();
		this.storeList = storeList;
		this.lat = lat;
		this.lng = lng;
		this.radius = radius;
	}

	public List<Store> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<Store> storeList) {
		this.storeList = storeList;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "CrmStoreLocation [storeList=" + storeList + ", lat=" + lat + ", lng=" + lng + "]";
	}
	
}
