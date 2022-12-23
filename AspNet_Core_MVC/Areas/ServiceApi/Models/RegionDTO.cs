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
    public class RegionDTO
    {
		[Required(ErrorMessage = "Required")]
		public int RegionID { get; set; }

		[Required(ErrorMessage = "Required")]
		public string RegionDescription { get; set; }

        public static RegionDTO ToDataTransferObject(Region item)
        {
            if (item == null)
                return null;

            return new RegionDTO
            {
				RegionID = item.RegionID,
				RegionDescription = item.RegionDescription,
            };
        }

        public static Region FromDataTransferObject(RegionDTO item)
        {
            if (item == null)
                return null;

            return new Region
            {
				RegionID = item.RegionID,
				RegionDescription = item.RegionDescription,
            };
        }
    }
}