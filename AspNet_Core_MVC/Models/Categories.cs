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
    public class Categories
    {
	[Required(ErrorMessage = "Required")]
	public int CategoryID { get; set; }

	[Required(ErrorMessage = "Required")]
	public string CategoryName { get; set; }

	[MaxLength(2000)]
	public string Description { get; set; }

	[MaxLength(255)]
	public string Picture { get; set; }

	public List<Products> ProductsList { get; set; }

        public Categories()
        { 
		ProductsList = new List<Products>();
        }

		public Categories(int CategoryID, string CategoryName, string Description, string Picture)
	{
		this.CategoryID = CategoryID;
		this.CategoryName = CategoryName;
		this.Description = Description;
		this.Picture = Picture;
	}
    }
}