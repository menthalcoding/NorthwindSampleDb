///////////////////////////////////////
// Author: Orhan Erdebil
// CreatedDate: 23 Aralık 2022 Cuma
// License terms are specified in the "license.txt" file in the root directory.
///////////////////////////////////////
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NorthwindSampleDb.Models;
using OnlineCoding.Code.Database;
using OnlineCoding.Code.Helper;

namespace NorthwindSampleDb.Repositories
{
    internal class RegionRepository //: IRepository_Region
    {
        #region IRegionDao Members

        internal void Insert(Region data)
        {
            string sql = @"exec sp_Region_Insert @RegionID, @RegionDescription"; 

            Db.Insert(sql, Take(data)); 
        }

        internal Region Get(int RegionID)
        {
            string sql = @"exec sp_Region_Select @RegionID";

            return Db.Read(sql, Make, new object[] { "@RegionID", RegionID }); 
        }

		internal List<Territories> GetTerritoriesList(int RegionID)
		{
			string sql = @"exec sp_Region_Territories_List @RegionID";

			return Db.ReadList(sql, TerritoriesRepository.Make, new object[] { "@RegionID", RegionID });
		}

        internal List<Region> List(int startIndex, int itemCount)
        {
            string sql = @"exec sp_Region_List @startIndex, @itemCount";

            return Db.ReadList(sql, Make, new object[] { "@startIndex", startIndex, "@itemCount", itemCount }); 
        }

        internal void Update(Region data)
        {
            string sql = @"exec sp_Region_Update @RegionID, @RegionDescription";

            Db.Update(sql, Take(data));
        }

        internal void Delete(int RegionID)
        {
            string sql = @"exec sp_Region_Delete @RegionID";

            Db.Delete(sql, new object[] { "@RegionID", RegionID });
        }

        #endregion
        
        /// <summary>
        /// Creates a Region object based on DataReader.
        /// </summary>
        internal static Func<IDataReader, Region> Make = reader =>
           new Region
           {
				RegionID = DbHelper.ConvertFromDBVal<int>(reader["RegionID"]),
				RegionDescription = DbHelper.ConvertFromDBVal<string>(reader["RegionDescription"])
           };

        /// <summary>
        /// Creates query parameters list from Region object
        /// </summary>
        /// <param name="Region">Region.</param>
        /// <returns>Name value parameter list.</returns>
        private object[] Take(Region data)
        {
            return new object[]  
            {
				"@RegionID", data.RegionID,
				"@RegionDescription", data.RegionDescription
            };
        }
    }
}
