package HttpClient.DataTransferObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDto {

	@SerializedName("orderId")
	@Expose
	private Integer OrderId;

	@SerializedName("customerID")
	@Expose
	private String CustomerID;

	@SerializedName("employeeID")
	@Expose
	private Integer EmployeeID;

	@SerializedName("orderDate")
	@Expose
	private Date OrderDate;

	@SerializedName("requiredDate")
	@Expose
	private Date RequiredDate;

	@SerializedName("shippedDate")
	@Expose
	private Date ShippedDate;

	@SerializedName("shipName")
	@Expose
	private String ShipName;

	@SerializedName("shipVia")
	@Expose
	private Integer ShipVia;

	@SerializedName("freight")
	@Expose
	private BigDecimal Freight;

	@SerializedName("shipAddress")
	@Expose
	private String ShipAddress;

	@SerializedName("shipCity")
	@Expose
	private String ShipCity;

	@SerializedName("shipRegion")
	@Expose
	private String ShipRegion;

	@SerializedName("shipPostalCode")
	@Expose
	private String ShipPostalCode;

	@SerializedName("shipCountry")
	@Expose
	private String ShipCountry;

	public Integer getOrderId() { return OrderId; }

	public void setOrderId(Integer OrderId) { this.OrderId = OrderId; }

	public String getCustomerID() { return (CustomerID == null ? new String() : CustomerID); }

	public void setCustomerID(String CustomerID) { this.CustomerID = CustomerID; }

	public Integer getEmployeeID() { return EmployeeID; }

	public void setEmployeeID(Integer EmployeeID) { this.EmployeeID = EmployeeID; }

	public Date getOrderDate() { return OrderDate; }

	public void setOrderDate(Date OrderDate) { this.OrderDate = OrderDate; }

	public Date getRequiredDate() { return RequiredDate; }

	public void setRequiredDate(Date RequiredDate) { this.RequiredDate = RequiredDate; }

	public Date getShippedDate() { return ShippedDate; }

	public void setShippedDate(Date ShippedDate) { this.ShippedDate = ShippedDate; }

	public String getShipName() { return (ShipName == null ? new String() : ShipName); }

	public void setShipName(String ShipName) { this.ShipName = ShipName; }

	public Integer getShipVia() { return ShipVia; }

	public void setShipVia(Integer ShipVia) { this.ShipVia = ShipVia; }

	public BigDecimal getFreight() { return Freight; }

	public void setFreight(BigDecimal Freight) { this.Freight = Freight; }

	public String getShipAddress() { return (ShipAddress == null ? new String() : ShipAddress); }

	public void setShipAddress(String ShipAddress) { this.ShipAddress = ShipAddress; }

	public String getShipCity() { return (ShipCity == null ? new String() : ShipCity); }

	public void setShipCity(String ShipCity) { this.ShipCity = ShipCity; }

	public String getShipRegion() { return (ShipRegion == null ? new String() : ShipRegion); }

	public void setShipRegion(String ShipRegion) { this.ShipRegion = ShipRegion; }

	public String getShipPostalCode() { return (ShipPostalCode == null ? new String() : ShipPostalCode); }

	public void setShipPostalCode(String ShipPostalCode) { this.ShipPostalCode = ShipPostalCode; }

	public String getShipCountry() { return (ShipCountry == null ? new String() : ShipCountry); }

	public void setShipCountry(String ShipCountry) { this.ShipCountry = ShipCountry; }

}