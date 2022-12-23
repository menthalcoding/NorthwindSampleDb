package HttpClient.DataTransferObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class RegionDto {

	@SerializedName("regionID")
	@Expose
	private Integer RegionID;

	@SerializedName("regionDescription")
	@Expose
	private String RegionDescription;

	public Integer getRegionID() { return RegionID; }

	public void setRegionID(Integer RegionID) { this.RegionID = RegionID; }

	public String getRegionDescription() { return (RegionDescription == null ? new String() : RegionDescription); }

	public void setRegionDescription(String RegionDescription) { this.RegionDescription = RegionDescription; }

}