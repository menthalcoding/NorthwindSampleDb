package HttpClient.DataTransferObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class SuppliersDto {

	@SerializedName("supplierID")
	@Expose
	private Integer SupplierID;

	@SerializedName("companyName")
	@Expose
	private String CompanyName;

	@SerializedName("contactName")
	@Expose
	private String ContactName;

	@SerializedName("contactTitle")
	@Expose
	private String ContactTitle;

	@SerializedName("address")
	@Expose
	private String Address;

	@SerializedName("city")
	@Expose
	private String City;

	@SerializedName("region")
	@Expose
	private String Region;

	@SerializedName("postalCode")
	@Expose
	private String PostalCode;

	@SerializedName("country")
	@Expose
	private String Country;

	@SerializedName("phone")
	@Expose
	private String Phone;

	@SerializedName("fax")
	@Expose
	private String Fax;

	@SerializedName("homePage")
	@Expose
	private String HomePage;

	public Integer getSupplierID() { return SupplierID; }

	public void setSupplierID(Integer SupplierID) { this.SupplierID = SupplierID; }

	public String getCompanyName() { return (CompanyName == null ? new String() : CompanyName); }

	public void setCompanyName(String CompanyName) { this.CompanyName = CompanyName; }

	public String getContactName() { return (ContactName == null ? new String() : ContactName); }

	public void setContactName(String ContactName) { this.ContactName = ContactName; }

	public String getContactTitle() { return (ContactTitle == null ? new String() : ContactTitle); }

	public void setContactTitle(String ContactTitle) { this.ContactTitle = ContactTitle; }

	public String getAddress() { return (Address == null ? new String() : Address); }

	public void setAddress(String Address) { this.Address = Address; }

	public String getCity() { return (City == null ? new String() : City); }

	public void setCity(String City) { this.City = City; }

	public String getRegion() { return (Region == null ? new String() : Region); }

	public void setRegion(String Region) { this.Region = Region; }

	public String getPostalCode() { return (PostalCode == null ? new String() : PostalCode); }

	public void setPostalCode(String PostalCode) { this.PostalCode = PostalCode; }

	public String getCountry() { return (Country == null ? new String() : Country); }

	public void setCountry(String Country) { this.Country = Country; }

	public String getPhone() { return (Phone == null ? new String() : Phone); }

	public void setPhone(String Phone) { this.Phone = Phone; }

	public String getFax() { return (Fax == null ? new String() : Fax); }

	public void setFax(String Fax) { this.Fax = Fax; }

	public String getHomePage() { return (HomePage == null ? new String() : HomePage); }

	public void setHomePage(String HomePage) { this.HomePage = HomePage; }

}