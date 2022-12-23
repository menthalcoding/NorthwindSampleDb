///////////////////////////////////////
// Author: Orhan Erdebil
// CreatedDate: 23 Aralık 2022 Cuma
// License terms are specified in the "license.txt" file in the root directory.
///////////////////////////////////////

using Microsoft.AspNetCore.Mvc;
using System.ComponentModel.DataAnnotations;
using NorthwindSampleDb.Models;

namespace NorthwindSampleDb.WebApi.Models
{
    // Remove the fields you do not want to be served
    public class OrderDetailsDTO
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

        public static OrderDetailsDTO ToDataTransferObject(OrderDetails item)
        {
            if (item == null)
                return null;

            return new OrderDetailsDTO
            {
				OrderId = item.OrderId,
				ProductId = item.ProductId,
				UnitPrice = item.UnitPrice,
				Quantity = item.Quantity,
				Discount = item.Discount,
            };
        }

        public static OrderDetails FromDataTransferObject(OrderDetailsDTO item)
        {
            if (item == null)
                return null;

            return new OrderDetails
            {
				OrderId = item.OrderId,
				ProductId = item.ProductId,
				UnitPrice = item.UnitPrice,
				Quantity = item.Quantity,
				Discount = item.Discount,
            };
        }
    }
}