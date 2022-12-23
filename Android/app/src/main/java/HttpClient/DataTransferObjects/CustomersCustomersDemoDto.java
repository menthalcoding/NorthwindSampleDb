package HttpClient.DataTransferObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class CustomersCustomersDemoDto {

	@SerializedName("customerID")
	@Expose
	private String CustomerID;

	@SerializedName("customersTypeId")
	@Expose
	private String CustomersTypeId;

	public String getCustomerID() { return (CustomerID == null ? new String() : CustomerID); }

	public void setCustomerID(String CustomerID) { this.CustomerID = CustomerID; }

	public String getCustomersTypeId() { return (CustomersTypeId == null ? new String() : CustomersTypeId); }

	public void setCustomersTypeId(String CustomersTypeId) { this.CustomersTypeId = CustomersTypeId; }

}