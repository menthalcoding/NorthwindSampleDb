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
    public class CustomersCustomersDemoDTO
    {
		[Required(ErrorMessage = "Required")]
		public string CustomerID { get; set; }

		[Required(ErrorMessage = "Required")]
		public string CustomersTypeId { get; set; }

        public static CustomersCustomersDemoDTO ToDataTransferObject(CustomersCustomersDemo item)
        {
            if (item == null)
                return null;

            return new CustomersCustomersDemoDTO
            {
				CustomerID = item.CustomerID,
				CustomersTypeId = item.CustomersTypeId,
            };
        }

        public static CustomersCustomersDemo FromDataTransferObject(CustomersCustomersDemoDTO item)
        {
            if (item == null)
                return null;

            return new CustomersCustomersDemo
            {
				CustomerID = item.CustomerID,
				CustomersTypeId = item.CustomersTypeId,
            };
        }
    }
}