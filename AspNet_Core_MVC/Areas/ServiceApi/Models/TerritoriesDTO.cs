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
    public class TerritoriesDTO
    {
		[Required(ErrorMessage = "Required")]
		public string TerritoryID { get; set; }

		[Required(ErrorMessage = "Required")]
		public string TerritoryDescription { get; set; }

		[Required(ErrorMessage = "Required")]
		public int RegionID { get; set; }

        public static TerritoriesDTO ToDataTransferObject(Territories item)
        {
            if (item == null)
                return null;

            return new TerritoriesDTO
            {
				TerritoryID = item.TerritoryID,
				TerritoryDescription = item.TerritoryDescription,
				RegionID = item.RegionID,
            };
        }

        public static Territories FromDataTransferObject(TerritoriesDTO item)
        {
            if (item == null)
                return null;

            return new Territories
            {
				TerritoryID = item.TerritoryID,
				TerritoryDescription = item.TerritoryDescription,
				RegionID = item.RegionID,
            };
        }
    }
}