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
    public class Customers
    {
	public string CustomerID { get; set; }

	[MaxLength(50)]
	public string CompanyName { get; set; }

	[MaxLength(30)]
	public string ContactName { get; set; }

	[MaxLength(30)]
	public string ContactTitle { get; set; }

	[MaxLength(60)]
	public string Address { get; set; }

	[MaxLength(15)]
	public string City { get; set; }

	[MaxLength(15)]
	public string Region { get; set; }

	[MaxLength(12)]
	public string PostalCode { get; set; }

	[MaxLength(15)]
	public string Country { get; set; }

	[System.ComponentModel.DataAnnotations.PhoneAttribute]
	public string Phone { get; set; }

	[System.ComponentModel.DataAnnotations.PhoneAttribute]
	public string Fax { get; set; }

	public List<Order> OrderList { get; set; }

	public List<CustomersCustomersDemo> CustomersCustomersDemoList { get; set; }

        public Customers()
        { 
		OrderList = new List<Order>();
		CustomersCustomersDemoList = new List<CustomersCustomersDemo>();
        }

		public Customers(string CustomerID, string CompanyName, string ContactName, string ContactTitle, string Address, string City, string Region, string PostalCode, string Country, string Phone, string Fax)
	{
		this.CustomerID = CustomerID;
		this.CompanyName = CompanyName;
		this.ContactName = ContactName;
		this.ContactTitle = ContactTitle;
		this.Address = Address;
		this.City = City;
		this.Region = Region;
		this.PostalCode = PostalCode;
		this.Country = Country;
		this.Phone = Phone;
		this.Fax = Fax;
	}
    }
}