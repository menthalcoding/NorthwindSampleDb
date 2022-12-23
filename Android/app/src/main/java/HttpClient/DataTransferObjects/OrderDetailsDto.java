package HttpClient.DataTransferObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDetailsDto {

	@SerializedName("orderId")
	@Expose
	private Integer OrderId;

	@SerializedName("productId")
	@Expose
	private Integer ProductId;

	@SerializedName("unitPrice")
	@Expose
	private Double UnitPrice;

	@SerializedName("quantity")
	@Expose
	private Short Quantity;

	@SerializedName("discount")
	@Expose
	private Double Discount;

	public Integer getOrderId() { return OrderId; }

	public void setOrderId(Integer OrderId) { this.OrderId = OrderId; }

	public Integer getProductId() { return ProductId; }

	public void setProductId(Integer ProductId) { this.ProductId = ProductId; }

	public Double getUnitPrice() { return UnitPrice; }

	public void setUnitPrice(Double UnitPrice) { this.UnitPrice = UnitPrice; }

	public Short getQuantity() { return Quantity; }

	public void setQuantity(Short Quantity) { this.Quantity = Quantity; }

	public Double getDiscount() { return Discount; }

	public void setDiscount(Double Discount) { this.Discount = Discount; }

}