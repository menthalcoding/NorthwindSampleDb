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
    public class EmployeesTerritories
    {
	[Required(ErrorMessage = "Required")]
	public int EmpleymontId { get; set; }

	[Required(ErrorMessage = "Required")]
	public string TerritoryID { get; set; }

        public EmployeesTerritories()
        { 
        }

		public EmployeesTerritories(int EmpleymontId, string TerritoryID)
	{
		this.EmpleymontId = EmpleymontId;
		this.TerritoryID = TerritoryID;
	}
    }
}