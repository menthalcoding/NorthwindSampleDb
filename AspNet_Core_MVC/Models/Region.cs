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
    public class Region
    {
	[Required(ErrorMessage = "Required")]
	public int RegionID { get; set; }

	[Required(ErrorMessage = "Required")]
	public string RegionDescription { get; set; }

	public List<Territories> TerritoriesList { get; set; }

        public Region()
        { 
		TerritoriesList = new List<Territories>();
        }

		public Region(int RegionID, string RegionDescription)
	{
		this.RegionID = RegionID;
		this.RegionDescription = RegionDescription;
	}
    }
}