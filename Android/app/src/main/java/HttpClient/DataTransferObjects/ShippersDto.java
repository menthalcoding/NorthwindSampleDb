package HttpClient.DataTransferObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class ShippersDto {

	@SerializedName("shipperId")
	@Expose
	private Integer ShipperId;

	@SerializedName("companyName")
	@Expose
	private String CompanyName;

	@SerializedName("phone")
	@Expose
	private String Phone;

	public Integer getShipperId() { return ShipperId; }

	public void setShipperId(Integer ShipperId) { this.ShipperId = ShipperId; }

	public String getCompanyName() { return (CompanyName == null ? new String() : CompanyName); }

	public void setCompanyName(String CompanyName) { this.CompanyName = CompanyName; }

	public String getPhone() { return (Phone == null ? new String() : Phone); }

	public void setPhone(String Phone) { this.Phone = Phone; }

}