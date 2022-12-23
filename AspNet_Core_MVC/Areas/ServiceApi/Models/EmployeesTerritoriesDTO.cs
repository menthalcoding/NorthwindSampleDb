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
    public class EmployeesTerritoriesDTO
    {
		[Required(ErrorMessage = "Required")]
		public int EmpleymontId { get; set; }

		[Required(ErrorMessage = "Required")]
		public string TerritoryID { get; set; }

        public static EmployeesTerritoriesDTO ToDataTransferObject(EmployeesTerritories item)
        {
            if (item == null)
                return null;

            return new EmployeesTerritoriesDTO
            {
				EmpleymontId = item.EmpleymontId,
				TerritoryID = item.TerritoryID,
            };
        }

        public static EmployeesTerritories FromDataTransferObject(EmployeesTerritoriesDTO item)
        {
            if (item == null)
                return null;

            return new EmployeesTerritories
            {
				EmpleymontId = item.EmpleymontId,
				TerritoryID = item.TerritoryID,
            };
        }
    }
}