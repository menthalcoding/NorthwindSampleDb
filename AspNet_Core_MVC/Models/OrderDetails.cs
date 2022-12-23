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
    public class OrderDetails
    {
	[Required(ErrorMessage = "Required")]
	public int OrderId { get; set; }

	[Required(ErrorMessage = "Required")]
	public int ProductId { get; set; }

	[Required(ErrorMessage = "Required")]
	public double UnitPrice { get; set; }

	[Required(ErrorMessage = "Required")]
	public short Quantity { get; set; }

	[Required(ErrorMessage = "Required")]
	public double Discount { get; set; }

        public OrderDetails()
        { 
        }

		public OrderDetails(int OrderId, int ProductId, double UnitPrice, short Quantity, double Discount)
	{
		this.OrderId = OrderId;
		this.ProductId = ProductId;
		this.UnitPrice = UnitPrice;
		this.Quantity = Quantity;
		this.Discount = Discount;
	}
    }
}