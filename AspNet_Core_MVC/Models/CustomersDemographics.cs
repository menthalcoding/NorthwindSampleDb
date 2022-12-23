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
    public class CustomersDemographics
    {
	[Required(ErrorMessage = "Required")]
	public string CustomersTypeId { get; set; }

	[MaxLength(2000)]
	public string CustomersDesc { get; set; }

	public List<CustomersCustomersDemo> CustomersCustomersDemoList { get; set; }

        public CustomersDemographics()
        { 
		CustomersCustomersDemoList = new List<CustomersCustomersDemo>();
        }

		public CustomersDemographics(string CustomersTypeId, string CustomersDesc)
	{
		this.CustomersTypeId = CustomersTypeId;
		this.CustomersDesc = CustomersDesc;
	}
    }
}