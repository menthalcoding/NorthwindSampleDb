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
    public class CustomersCustomersDemo
    {
	[Required(ErrorMessage = "Required")]
	public string CustomerID { get; set; }

	[Required(ErrorMessage = "Required")]
	public string CustomersTypeId { get; set; }

        public CustomersCustomersDemo()
        { 
        }

		public CustomersCustomersDemo(string CustomerID, string CustomersTypeId)
	{
		this.CustomerID = CustomerID;
		this.CustomersTypeId = CustomersTypeId;
	}
    }
}