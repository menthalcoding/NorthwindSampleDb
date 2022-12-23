///////////////////////////////////////
// Author: Orhan Erdebil
// CreatedDate: 23 Aralık 2022 Cuma
// License terms are specified in the "license.txt" file in the root directory.
///////////////////////////////////////

using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations; 

namespace NorthwindSampleDb.Models 
{
    public class Products
    {
	[Required(ErrorMessage = "Required")]
	public int ProductId { get; set; }

	[Required(ErrorMessage = "Required")]
	public string ProductName { get; set; }

	
	public int? SupplierID { get; set; }

	
	public int? CategoryID { get; set; }

	[MaxLength(20)]
	public string QuantityPerUnit { get; set; }

	
	public double? UnitPrice { get; set; }

	
	public short? UnitsInStock { get; set; }

	
	public short? UnitsOnOrder { get; set; }

	
	public short? ReorderLevel { get; set; }

	[Required(ErrorMessage = "Required")]
	public bool Discontinued { get; set; }

	public List<OrderDetails> OrderDetailsList { get; set; }

        public Products()
        { 
		OrderDetailsList = new List<OrderDetails>();
        }

		public Products(int ProductId, string ProductName, int? SupplierID, int? CategoryID, string QuantityPerUnit, double? UnitPrice, short? UnitsInStock, short? UnitsOnOrder, short? ReorderLevel, bool Discontinued)
	{
		this.ProductId = ProductId;
		this.ProductName = ProductName;
		this.SupplierID = SupplierID;
		this.CategoryID = CategoryID;
		this.QuantityPerUnit = QuantityPerUnit;
		this.UnitPrice = UnitPrice;
		this.UnitsInStock = UnitsInStock;
		this.UnitsOnOrder = UnitsOnOrder;
		this.ReorderLevel = ReorderLevel;
		this.Discontinued = Discontinued;
	}
    }
}