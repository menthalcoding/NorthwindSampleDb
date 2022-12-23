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
    internal class EmployeesTerritoriesRepository //: IRepository_EmployeesTerritories
    {
        #region IEmployeesTerritoriesDao Members

        internal void Insert(EmployeesTerritories data)
        {
            string sql = @"exec sp_EmployeesTerritories_Insert @EmpleymontId, @TerritoryID"; 

            Db.Insert(sql, Take(data)); 
        }

        internal EmployeesTerritories Get(int EmpleymontId, string TerritoryID)
        {
            string sql = @"exec sp_EmployeesTerritories_Select @EmpleymontId, @TerritoryID";

            return Db.Read(sql, Make, new object[] { "@EmpleymontId", EmpleymontId, "@TerritoryID", TerritoryID }); 
        }

        internal List<EmployeesTerritories> List(int startIndex, int itemCount)
        {
            string sql = @"exec sp_EmployeesTerritories_List @startIndex, @itemCount";

            return Db.ReadList(sql, Make, new object[] { "@startIndex", startIndex, "@itemCount", itemCount }); 
        }

        internal void Update(EmployeesTerritories data)
        {
            string sql = @"exec sp_EmployeesTerritories_Update @EmpleymontId, @TerritoryID";

            Db.Update(sql, Take(data));
        }

        internal void Delete(int EmpleymontId, string TerritoryID)
        {
            string sql = @"exec sp_EmployeesTerritories_Delete @EmpleymontId, @TerritoryID";

            Db.Delete(sql, new object[] { "@EmpleymontId", EmpleymontId, "@TerritoryID", TerritoryID });
        }

        #endregion
        
        /// <summary>
        /// Creates a EmployeesTerritories object based on DataReader.
        /// </summary>
        internal static Func<IDataReader, EmployeesTerritories> Make = reader =>
           new EmployeesTerritories
           {
				EmpleymontId = DbHelper.ConvertFromDBVal<int>(reader["EmpleymontId"]),
				TerritoryID = DbHelper.ConvertFromDBVal<string>(reader["TerritoryID"])
           };

        /// <summary>
        /// Creates query parameters list from EmployeesTerritories object
        /// </summary>
        /// <param name="EmployeesTerritories">EmployeesTerritories.</param>
        /// <returns>Name value parameter list.</returns>
        private object[] Take(EmployeesTerritories data)
        {
            return new object[]  
            {
				"@EmpleymontId", data.EmpleymontId,
				"@TerritoryID", data.TerritoryID
            };
        }
    }
}
