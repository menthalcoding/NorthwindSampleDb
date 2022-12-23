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
    public class Employees
    {
	public int EmployeeID { get; set; }

	public string LastName { get; set; }

	[MaxLength(50)]
	public string FirstName { get; set; }

	[MaxLength(30)]
	public string Title { get; set; }

	[MaxLength(25)]
	public string TitleOfCourtesy { get; set; }

	[Range(typeof(DateTime), "1/1/1753", "1/1/9999", ErrorMessage = "Value for 1/1/1753 must be between 1/1/1753 and 1/1/9999")]
	public DateTime BirthDate { get; set; }

	[Range(typeof(DateTime), "1/1/1753", "1/1/9999", ErrorMessage = "Value for 1/1/1753 must be between 1/1/1753 and 1/1/9999")]
	public DateTime? HireDate { get; set; }

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
	public string HomePhone { get; set; }

	[MaxLength(4)]
	public string Extension { get; set; }

	[MaxLength(255)]
	public string Photo { get; set; }

	[MaxLength(2000)]
	public string Notes { get; set; }

	
	public int? ReportsTo { get; set; }

	[MaxLength(255)]
	public string PhotoPath { get; set; }

	public List<Order> OrderList { get; set; }

	public List<EmployeesTerritories> EmployeesTerritoriesList { get; set; }

        public Employees()
        { 
		OrderList = new List<Order>();
		EmployeesTerritoriesList = new List<EmployeesTerritories>();
        }

		public Employees(int EmployeeID, string LastName, string FirstName, string Title, string TitleOfCourtesy, DateTime BirthDate, DateTime? HireDate, string Address, string City, string Region, string PostalCode, string Country, string HomePhone, string Extension, string Photo, string Notes, int? ReportsTo, string PhotoPath)
	{
		this.EmployeeID = EmployeeID;
		this.LastName = LastName;
		this.FirstName = FirstName;
		this.Title = Title;
		this.TitleOfCourtesy = TitleOfCourtesy;
		this.BirthDate = BirthDate;
		this.HireDate = HireDate;
		this.Address = Address;
		this.City = City;
		this.Region = Region;
		this.PostalCode = PostalCode;
		this.Country = Country;
		this.HomePhone = HomePhone;
		this.Extension = Extension;
		this.Photo = Photo;
		this.Notes = Notes;
		this.ReportsTo = ReportsTo;
		this.PhotoPath = PhotoPath;
	}
    }
}