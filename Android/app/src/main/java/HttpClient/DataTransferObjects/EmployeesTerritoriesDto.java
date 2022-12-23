package HttpClient.DataTransferObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class EmployeesTerritoriesDto {

	@SerializedName("empleymontId")
	@Expose
	private Integer EmpleymontId;

	@SerializedName("territoryID")
	@Expose
	private String TerritoryID;

	public Integer getEmpleymontId() { return EmpleymontId; }

	public void setEmpleymontId(Integer EmpleymontId) { this.EmpleymontId = EmpleymontId; }

	public String getTerritoryID() { return (TerritoryID == null ? new String() : TerritoryID); }

	public void setTerritoryID(String TerritoryID) { this.TerritoryID = TerritoryID; }

}