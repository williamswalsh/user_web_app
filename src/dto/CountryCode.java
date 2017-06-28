/**
 * Title:			CountryCode.java
 * @author			William Walsh
 * @version			1.0
 * @since			28-6-2017
 * Purpose:			CountryCode is a class used to store data about a countries mobile extension.
 * 					It contains a String of the Country and a integer of the number extension itself.
 * */
package dto;

public class CountryCode {
	
	private String countryName;
	private int countryExt;
	
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getCountryExt() {
		return countryExt;
	}
	public void setCountryExt(int countryExt) {
		this.countryExt = countryExt;
	}
}
