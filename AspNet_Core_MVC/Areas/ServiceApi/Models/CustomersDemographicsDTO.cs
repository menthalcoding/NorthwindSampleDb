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
    public class CustomersDemographicsDTO
    {
		[Required(ErrorMessage = "Required")]
		public string CustomersTypeId { get; set; }

		[MaxLength(2000)]
		public string CustomersDesc { get; set; }

        public static CustomersDemographicsDTO ToDataTransferObject(CustomersDemographics item)
        {
            if (item == null)
                return null;

            return new CustomersDemographicsDTO
            {
				CustomersTypeId = item.CustomersTypeId,
				CustomersDesc = item.CustomersDesc,
            };
        }

        public static CustomersDemographics FromDataTransferObject(CustomersDemographicsDTO item)
        {
            if (item == null)
                return null;

            return new CustomersDemographics
            {
				CustomersTypeId = item.CustomersTypeId,
				CustomersDesc = item.CustomersDesc,
            };
        }
    }
}