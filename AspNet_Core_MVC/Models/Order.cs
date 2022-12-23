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
    public class Order
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

	public List<OrderDetails> OrderDetailsList { get; set; }

        public Order()
        { 
		OrderDetailsList = new List<OrderDetails>();
        }

		public Order(int OrderId, string CustomerID, int EmployeeID, DateTime OrderDate, DateTime? RequiredDate, DateTime? ShippedDate, string ShipName, int? ShipVia, decimal? Freight, string ShipAddress, string ShipCity, string ShipRegion, string ShipPostalCode, string ShipCountry)
	{
		this.OrderId = OrderId;
		this.CustomerID = CustomerID;
		this.EmployeeID = EmployeeID;
		this.OrderDate = OrderDate;
		this.RequiredDate = RequiredDate;
		this.ShippedDate = ShippedDate;
		this.ShipName = ShipName;
		this.ShipVia = ShipVia;
		this.Freight = Freight;
		this.ShipAddress = ShipAddress;
		this.ShipCity = ShipCity;
		this.ShipRegion = ShipRegion;
		this.ShipPostalCode = ShipPostalCode;
		this.ShipCountry = ShipCountry;
	}
    }
}