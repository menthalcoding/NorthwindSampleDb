package HttpClient.DataTransferObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class TerritoriesDto {

	@SerializedName("territoryID")
	@Expose
	private String TerritoryID;

	@SerializedName("territoryDescription")
	@Expose
	private String TerritoryDescription;

	@SerializedName("regionID")
	@Expose
	private Integer RegionID;

	public String getTerritoryID() { return (TerritoryID == null ? new String() : TerritoryID); }

	public void setTerritoryID(String TerritoryID) { this.TerritoryID = TerritoryID; }

	public String getTerritoryDescription() { return (TerritoryDescription == null ? new String() : TerritoryDescription); }

	public void setTerritoryDescription(String TerritoryDescription) { this.TerritoryDescription = TerritoryDescription; }

	public Integer getRegionID() { return RegionID; }

	public void setRegionID(Integer RegionID) { this.RegionID = RegionID; }

}