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
    internal class TerritoriesRepository //: IRepository_Territories
    {
        #region ITerritoriesDao Members

        internal void Insert(Territories data)
        {
            string sql = @"exec sp_Territories_Insert @TerritoryID, @TerritoryDescription, @RegionID"; 

            Db.Insert(sql, Take(data)); 
        }

        internal Territories Get(string TerritoryID)
        {
            string sql = @"exec sp_Territories_Select @TerritoryID";

            return Db.Read(sql, Make, new object[] { "@TerritoryID", TerritoryID }); 
        }

		internal List<EmployeesTerritories> GetEmployeesTerritoriesList(string TerritoryID)
		{
			string sql = @"exec sp_Territories_EmployeesTerritories_List @TerritoryID";

			return Db.ReadList(sql, EmployeesTerritoriesRepository.Make, new object[] { "@TerritoryID", TerritoryID });
		}

        internal List<Territories> List(int startIndex, int itemCount)
        {
            string sql = @"exec sp_Territories_List @startIndex, @itemCount";

            return Db.ReadList(sql, Make, new object[] { "@startIndex", startIndex, "@itemCount", itemCount }); 
        }

        internal void Update(Territories data)
        {
            string sql = @"exec sp_Territories_Update @TerritoryID, @TerritoryDescription, @RegionID";

            Db.Update(sql, Take(data));
        }

        internal void Delete(string TerritoryID)
        {
            string sql = @"exec sp_Territories_Delete @TerritoryID";

            Db.Delete(sql, new object[] { "@TerritoryID", TerritoryID });
        }

        #endregion
        
        /// <summary>
        /// Creates a Territories object based on DataReader.
        /// </summary>
        internal static Func<IDataReader, Territories> Make = reader =>
           new Territories
           {
				TerritoryID = DbHelper.ConvertFromDBVal<string>(reader["TerritoryID"]),
				TerritoryDescription = DbHelper.ConvertFromDBVal<string>(reader["TerritoryDescription"]),
				RegionID = DbHelper.ConvertFromDBVal<int>(reader["RegionID"])
           };

        /// <summary>
        /// Creates query parameters list from Territories object
        /// </summary>
        /// <param name="Territories">Territories.</param>
        /// <returns>Name value parameter list.</returns>
        private object[] Take(Territories data)
        {
            return new object[]  
            {
				"@TerritoryID", data.TerritoryID,
				"@TerritoryDescription", data.TerritoryDescription,
				"@RegionID", data.RegionID
            };
        }
    }
}
