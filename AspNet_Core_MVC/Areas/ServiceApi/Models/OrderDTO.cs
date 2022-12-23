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
    public class OrderDTO
    {
		public int OrderId { get; set; }

		[Required(ErrorMessage = "Required")]
		public string CustomerID { get; set; }

		[Required(ErrorMessage = "Required")]
		public int EmployeeID { get; set; }

		public DateTime OrderDate { get; set; }

		[Range(typeof(DateTime), "1/1/1753", "1/1/9999", ErrorMessage = "Value for 1/1/1753 must be between 1/1/1753 and 1/1/9999")]
		public DateTime? RequiredDate { get; set; }

		[Range(typeof(DateTime), "1/1/1753", "1/1/9999", ErrorMessage = "Value for 1/1/1753 must be between 1/1/1753 and 1/1/9999")]
		public DateTime? ShippedDate { get; set; }

		[MaxLength(40)]
		public string ShipName { get; set; }

		public int? ShipVia { get; set; }

		public decimal? Freight { get; set; }

		[MaxLength(60)]
		public string ShipAddress { get; set; }

		[MaxLength(15)]
		public string ShipCity { get; set; }

		[MaxLength(15)]
		public string ShipRegion { get; set; }

		[MaxLength(10)]
		public string ShipPostalCode { get; set; }

		[MaxLength(15)]
		public string ShipCountry { get; set; }

        public static OrderDTO ToDataTransferObject(Order item)
        {
            if (item == null)
                return null;

            return new OrderDTO
            {
				OrderId = item.OrderId,
				CustomerID = item.CustomerID,
				EmployeeID = item.EmployeeID,
				OrderDate = item.OrderDate,
				RequiredDate = item.RequiredDate,
				ShippedDate = item.ShippedDate,
				ShipName = item.ShipName,
				ShipVia = item.ShipVia,
				Freight = item.Freight,
				ShipAddress = item.ShipAddress,
				ShipCity = item.ShipCity,
				ShipRegion = item.ShipRegion,
				ShipPostalCode = item.ShipPostalCode,
				ShipCountry = item.ShipCountry,
            };
        }

        public static Order FromDataTransferObject(OrderDTO item)
        {
            if (item == null)
                return null;

            return new Order
            {
				OrderId = item.OrderId,
				CustomerID = item.CustomerID,
				EmployeeID = item.EmployeeID,
				OrderDate = item.OrderDate,
				RequiredDate = item.RequiredDate,
				ShippedDate = item.ShippedDate,
				ShipName = item.ShipName,
				ShipVia = item.ShipVia,
				Freight = item.Freight,
				ShipAddress = item.ShipAddress,
				ShipCity = item.ShipCity,
				ShipRegion = item.ShipRegion,
				ShipPostalCode = item.ShipPostalCode,
				ShipCountry = item.ShipCountry,
            };
        }
    }
}