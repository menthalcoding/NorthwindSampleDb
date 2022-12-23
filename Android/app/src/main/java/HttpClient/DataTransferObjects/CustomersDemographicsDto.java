package HttpClient.DataTransferObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class CustomersDemographicsDto {

	@SerializedName("customersTypeId")
	@Expose
	private String CustomersTypeId;

	@SerializedName("customersDesc")
	@Expose
	private String CustomersDesc;

	public String getCustomersTypeId() { return (CustomersTypeId == null ? new String() : CustomersTypeId); }

	public void setCustomersTypeId(String CustomersTypeId) { this.CustomersTypeId = CustomersTypeId; }

	public String getCustomersDesc() { return (CustomersDesc == null ? new String() : CustomersDesc); }

	public void setCustomersDesc(String CustomersDesc) { this.CustomersDesc = CustomersDesc; }

}