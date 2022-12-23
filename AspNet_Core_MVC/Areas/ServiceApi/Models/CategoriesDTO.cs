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
    public class CategoriesDTO
    {
		[Required(ErrorMessage = "Required")]
		public int CategoryID { get; set; }

		[Required(ErrorMessage = "Required")]
		public string CategoryName { get; set; }

		[MaxLength(2000)]
		public string Description { get; set; }

		[MaxLength(255)]
		public string Picture { get; set; }

        public static CategoriesDTO ToDataTransferObject(Categories item)
        {
            if (item == null)
                return null;

            return new CategoriesDTO
            {
				CategoryID = item.CategoryID,
				CategoryName = item.CategoryName,
				Description = item.Description,
				Picture = item.Picture,
            };
        }

        public static Categories FromDataTransferObject(CategoriesDTO item)
        {
            if (item == null)
                return null;

            return new Categories
            {
				CategoryID = item.CategoryID,
				CategoryName = item.CategoryName,
				Description = item.Description,
				Picture = item.Picture,
            };
        }
    }
}