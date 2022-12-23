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
    public class Suppliers
    {
	[Required(ErrorMessage = "Required")]
	public int SupplierID { get; set; }

	[Required(ErrorMessage = "Required")]
	public string CompanyName { get; set; }

	[MaxLength(30)]
	public string ContactName { get; set; }

	[StringLength(30, MinimumLength = 1, ErrorMessage = "StringLength")]
	public string ContactTitle { get; set; }

	[MaxLength(60)]
	public string Address { get; set; }

	[MaxLength(15)]
	public string City { get; set; }

	[MaxLength(15)]
	public string Region { get; set; }

	[MaxLength(10)]
	public string PostalCode { get; set; }

	[MaxLength(15)]
	public string Country { get; set; }

	[System.ComponentModel.DataAnnotations.PhoneAttribute]
	public string Phone { get; set; }

	[System.ComponentModel.DataAnnotations.PhoneAttribute]
	public string Fax { get; set; }

	[MaxLength(2000)]
	public string HomePage { get; set; }

	public List<Products> ProductsList { get; set; }

        public Suppliers()
        { 
		ProductsList = new List<Products>();
        }

		public Suppliers(int SupplierID, string CompanyName, string ContactName, string ContactTitle, string Address, string City, string Region, string PostalCode, string Country, string Phone, string Fax, string HomePage)
	{
		this.SupplierID = SupplierID;
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
		this.HomePage = HomePage;
	}
    }
}