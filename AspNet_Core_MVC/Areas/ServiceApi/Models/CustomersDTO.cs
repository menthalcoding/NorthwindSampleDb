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
    public class CustomersDTO
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

        public static CustomersDTO ToDataTransferObject(Customers item)
        {
            if (item == null)
                return null;

            return new CustomersDTO
            {
				CustomerID = item.CustomerID,
				CompanyName = item.CompanyName,
				ContactName = item.ContactName,
				ContactTitle = item.ContactTitle,
				Address = item.Address,
				City = item.City,
				Region = item.Region,
				PostalCode = item.PostalCode,
				Country = item.Country,
				Phone = item.Phone,
				Fax = item.Fax,
            };
        }

        public static Customers FromDataTransferObject(CustomersDTO item)
        {
            if (item == null)
                return null;

            return new Customers
            {
				CustomerID = item.CustomerID,
				CompanyName = item.CompanyName,
				ContactName = item.ContactName,
				ContactTitle = item.ContactTitle,
				Address = item.Address,
				City = item.City,
				Region = item.Region,
				PostalCode = item.PostalCode,
				Country = item.Country,
				Phone = item.Phone,
				Fax = item.Fax,
            };
        }
    }
}