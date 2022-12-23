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
    public class ShippersDTO
    {
		[Required(ErrorMessage = "Required")]
		public int ShipperId { get; set; }

		[Required(ErrorMessage = "Required")]
		public string CompanyName { get; set; }

		[System.ComponentModel.DataAnnotations.PhoneAttribute]
		public string Phone { get; set; }

        public static ShippersDTO ToDataTransferObject(Shippers item)
        {
            if (item == null)
                return null;

            return new ShippersDTO
            {
				ShipperId = item.ShipperId,
				CompanyName = item.CompanyName,
				Phone = item.Phone,
            };
        }

        public static Shippers FromDataTransferObject(ShippersDTO item)
        {
            if (item == null)
                return null;

            return new Shippers
            {
				ShipperId = item.ShipperId,
				CompanyName = item.CompanyName,
				Phone = item.Phone,
            };
        }
    }
}