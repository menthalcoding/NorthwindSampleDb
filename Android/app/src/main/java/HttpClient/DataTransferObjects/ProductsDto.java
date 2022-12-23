package HttpClient.DataTransferObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class ProductsDto {

	@SerializedName("productId")
	@Expose
	private Integer ProductId;

	@SerializedName("productName")
	@Expose
	private String ProductName;

	@SerializedName("supplierID")
	@Expose
	private Integer SupplierID;

	@SerializedName("categoryID")
	@Expose
	private Integer CategoryID;

	@SerializedName("quantityPerUnit")
	@Expose
	private String QuantityPerUnit;

	@SerializedName("unitPrice")
	@Expose
	private Double UnitPrice;

	@SerializedName("unitsInStock")
	@Expose
	private Short UnitsInStock;

	@SerializedName("unitsOnOrder")
	@Expose
	private Short UnitsOnOrder;

	@SerializedName("reorderLevel")
	@Expose
	private Short ReorderLevel;

	@SerializedName("discontinued")
	@Expose
	private Boolean Discontinued;

	public Integer getProductId() { return ProductId; }

	public void setProductId(Integer ProductId) { this.ProductId = ProductId; }

	public String getProductName() { return (ProductName == null ? new String() : ProductName); }

	public void setProductName(String ProductName) { this.ProductName = ProductName; }

	public Integer getSupplierID() { return SupplierID; }

	public void setSupplierID(Integer SupplierID) { this.SupplierID = SupplierID; }

	public Integer getCategoryID() { return CategoryID; }

	public void setCategoryID(Integer CategoryID) { this.CategoryID = CategoryID; }

	public String getQuantityPerUnit() { return (QuantityPerUnit == null ? new String() : QuantityPerUnit); }

	public void setQuantityPerUnit(String QuantityPerUnit) { this.QuantityPerUnit = QuantityPerUnit; }

	public Double getUnitPrice() { return UnitPrice; }

	public void setUnitPrice(Double UnitPrice) { this.UnitPrice = UnitPrice; }

	public Short getUnitsInStock() { return UnitsInStock; }

	public void setUnitsInStock(Short UnitsInStock) { this.UnitsInStock = UnitsInStock; }

	public Short getUnitsOnOrder() { return UnitsOnOrder; }

	public void setUnitsOnOrder(Short UnitsOnOrder) { this.UnitsOnOrder = UnitsOnOrder; }

	public Short getReorderLevel() { return ReorderLevel; }

	public void setReorderLevel(Short ReorderLevel) { this.ReorderLevel = ReorderLevel; }

	public Boolean getDiscontinued() { return Discontinued; }

	public void setDiscontinued(Boolean Discontinued) { this.Discontinued = Discontinued; }

}