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
    public class Shippers
    {
	[Required(ErrorMessage = "Required")]
	public int ShipperId { get; set; }

	[Required(ErrorMessage = "Required")]
	public string CompanyName { get; set; }

	[System.ComponentModel.DataAnnotations.PhoneAttribute]
	public string Phone { get; set; }

	public List<Order> OrderList { get; set; }

        public Shippers()
        { 
		OrderList = new List<Order>();
        }

		public Shippers(int ShipperId, string CompanyName, string Phone)
	{
		this.ShipperId = ShipperId;
		this.CompanyName = CompanyName;
		this.Phone = Phone;
	}
    }
}