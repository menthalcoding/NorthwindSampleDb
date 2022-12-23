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
    public class EmployeesDTO
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

        public static EmployeesDTO ToDataTransferObject(Employees item)
        {
            if (item == null)
                return null;

            return new EmployeesDTO
            {
				EmployeeID = item.EmployeeID,
				LastName = item.LastName,
				FirstName = item.FirstName,
				Title = item.Title,
				TitleOfCourtesy = item.TitleOfCourtesy,
				BirthDate = item.BirthDate,
				HireDate = item.HireDate,
				Address = item.Address,
				City = item.City,
				Region = item.Region,
				PostalCode = item.PostalCode,
				Country = item.Country,
				HomePhone = item.HomePhone,
				Extension = item.Extension,
				Photo = item.Photo,
				Notes = item.Notes,
				ReportsTo = item.ReportsTo,
				PhotoPath = item.PhotoPath,
            };
        }

        public static Employees FromDataTransferObject(EmployeesDTO item)
        {
            if (item == null)
                return null;

            return new Employees
            {
				EmployeeID = item.EmployeeID,
				LastName = item.LastName,
				FirstName = item.FirstName,
				Title = item.Title,
				TitleOfCourtesy = item.TitleOfCourtesy,
				BirthDate = item.BirthDate,
				HireDate = item.HireDate,
				Address = item.Address,
				City = item.City,
				Region = item.Region,
				PostalCode = item.PostalCode,
				Country = item.Country,
				HomePhone = item.HomePhone,
				Extension = item.Extension,
				Photo = item.Photo,
				Notes = item.Notes,
				ReportsTo = item.ReportsTo,
				PhotoPath = item.PhotoPath,
            };
        }
    }
}