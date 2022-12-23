package HttpClient.DataTransferObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class EmployeesDto {

	@SerializedName("employeeID")
	@Expose
	private Integer EmployeeID;

	@SerializedName("lastName")
	@Expose
	private String LastName;

	@SerializedName("firstName")
	@Expose
	private String FirstName;

	@SerializedName("title")
	@Expose
	private String Title;

	@SerializedName("titleOfCourtesy")
	@Expose
	private String TitleOfCourtesy;

	@SerializedName("birthDate")
	@Expose
	private Date BirthDate;

	@SerializedName("hireDate")
	@Expose
	private Date HireDate;

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

	@SerializedName("homePhone")
	@Expose
	private String HomePhone;

	@SerializedName("extension")
	@Expose
	private String Extension;

	@SerializedName("photo")
	@Expose
	private String Photo;

	@SerializedName("notes")
	@Expose
	private String Notes;

	@SerializedName("reportsTo")
	@Expose
	private Integer ReportsTo;

	@SerializedName("photoPath")
	@Expose
	private String PhotoPath;

	public Integer getEmployeeID() { return EmployeeID; }

	public void setEmployeeID(Integer EmployeeID) { this.EmployeeID = EmployeeID; }

	public String getLastName() { return (LastName == null ? new String() : LastName); }

	public void setLastName(String LastName) { this.LastName = LastName; }

	public String getFirstName() { return (FirstName == null ? new String() : FirstName); }

	public void setFirstName(String FirstName) { this.FirstName = FirstName; }

	public String getTitle() { return (Title == null ? new String() : Title); }

	public void setTitle(String Title) { this.Title = Title; }

	public String getTitleOfCourtesy() { return (TitleOfCourtesy == null ? new String() : TitleOfCourtesy); }

	public void setTitleOfCourtesy(String TitleOfCourtesy) { this.TitleOfCourtesy = TitleOfCourtesy; }

	public Date getBirthDate() { return BirthDate; }

	public void setBirthDate(Date BirthDate) { this.BirthDate = BirthDate; }

	public Date getHireDate() { return HireDate; }

	public void setHireDate(Date HireDate) { this.HireDate = HireDate; }

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

	public String getHomePhone() { return (HomePhone == null ? new String() : HomePhone); }

	public void setHomePhone(String HomePhone) { this.HomePhone = HomePhone; }

	public String getExtension() { return (Extension == null ? new String() : Extension); }

	public void setExtension(String Extension) { this.Extension = Extension; }

	public String getPhoto() { return (Photo == null ? new String() : Photo); }

	public void setPhoto(String Photo) { this.Photo = Photo; }

	public String getNotes() { return (Notes == null ? new String() : Notes); }

	public void setNotes(String Notes) { this.Notes = Notes; }

	public Integer getReportsTo() { return ReportsTo; }

	public void setReportsTo(Integer ReportsTo) { this.ReportsTo = ReportsTo; }

	public String getPhotoPath() { return (PhotoPath == null ? new String() : PhotoPath); }

	public void setPhotoPath(String PhotoPath) { this.PhotoPath = PhotoPath; }

}