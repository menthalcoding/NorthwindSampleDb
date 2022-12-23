package HttpClient.DataTransferObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class CategoriesDto {

	@SerializedName("categoryID")
	@Expose
	private Integer CategoryID;

	@SerializedName("categoryName")
	@Expose
	private String CategoryName;

	@SerializedName("description")
	@Expose
	private String Description;

	@SerializedName("picture")
	@Expose
	private String Picture;

	public Integer getCategoryID() { return CategoryID; }

	public void setCategoryID(Integer CategoryID) { this.CategoryID = CategoryID; }

	public String getCategoryName() { return (CategoryName == null ? new String() : CategoryName); }

	public void setCategoryName(String CategoryName) { this.CategoryName = CategoryName; }

	public String getDescription() { return (Description == null ? new String() : Description); }

	public void setDescription(String Description) { this.Description = Description; }

	public String getPicture() { return (Picture == null ? new String() : Picture); }

	public void setPicture(String Picture) { this.Picture = Picture; }

}