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
    public class Territories
    {
	[Required(ErrorMessage = "Required")]
	public string TerritoryID { get; set; }

	[Required(ErrorMessage = "Required")]
	public string TerritoryDescription { get; set; }

	[Required(ErrorMessage = "Required")]
	public int RegionID { get; set; }

	public List<EmployeesTerritories> EmployeesTerritoriesList { get; set; }

        public Territories()
        { 
		EmployeesTerritoriesList = new List<EmployeesTerritories>();
        }

		public Territories(string TerritoryID, string TerritoryDescription, int RegionID)
	{
		this.TerritoryID = TerritoryID;
		this.TerritoryDescription = TerritoryDescription;
		this.RegionID = RegionID;
	}
    }
}