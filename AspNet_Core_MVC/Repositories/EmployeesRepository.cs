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
    internal class EmployeesRepository //: IRepository_Employees
    {
        #region IEmployeesDao Members

        internal void Insert(Employees data)
        {
            string sql = @"exec sp_Employees_Insert @EmployeeID, @LastName, @FirstName, @Title, @TitleOfCourtesy, @BirthDate, @HireDate, @Address, @City, @Region, @PostalCode, @Country, @HomePhone, @Extension, @Photo, @Notes, @ReportsTo, @PhotoPath"; 

            Db.Insert(sql, Take(data)); 
        }

        internal Employees Get(int EmployeeID)
        {
            string sql = @"exec sp_Employees_Select @EmployeeID";

            return Db.Read(sql, Make, new object[] { "@EmployeeID", EmployeeID }); 
        }

		internal List<Order> GetOrderList(int EmployeeID)
		{
			string sql = @"exec sp_Employees_Order_List @EmployeeID";

			return Db.ReadList(sql, OrderRepository.Make, new object[] { "@EmployeeID", EmployeeID });
		}

		internal List<EmployeesTerritories> GetEmployeesTerritoriesList(int EmployeeID)
		{
			string sql = @"exec sp_Employees_EmployeesTerritories_List @EmployeeID";

			return Db.ReadList(sql, EmployeesTerritoriesRepository.Make, new object[] { "@EmployeeID", EmployeeID });
		}

        internal List<Employees> List(int startIndex, int itemCount)
        {
            string sql = @"exec sp_Employees_List @startIndex, @itemCount";

            return Db.ReadList(sql, Make, new object[] { "@startIndex", startIndex, "@itemCount", itemCount }); 
        }

        internal void Update(Employees data)
        {
            string sql = @"exec sp_Employees_Update @EmployeeID, @LastName, @FirstName, @Title, @TitleOfCourtesy, @BirthDate, @HireDate, @Address, @City, @Region, @PostalCode, @Country, @HomePhone, @Extension, @Photo, @Notes, @ReportsTo, @PhotoPath";

            Db.Update(sql, Take(data));
        }

        internal void Delete(int EmployeeID)
        {
            string sql = @"exec sp_Employees_Delete @EmployeeID";

            Db.Delete(sql, new object[] { "@EmployeeID", EmployeeID });
        }

        #endregion
        
        /// <summary>
        /// Creates a Employees object based on DataReader.
        /// </summary>
        internal static Func<IDataReader, Employees> Make = reader =>
           new Employees
           {
				EmployeeID = DbHelper.ConvertFromDBVal<int>(reader["EmployeeID"]),
				LastName = DbHelper.ConvertFromDBVal<string>(reader["LastName"]),
				FirstName = DbHelper.ConvertFromDBVal<string>(reader["FirstName"]),
				Title = DbHelper.ConvertFromDBVal<string>(reader["Title"]),
				TitleOfCourtesy = DbHelper.ConvertFromDBVal<string>(reader["TitleOfCourtesy"]),
				BirthDate = DbHelper.ConvertFromDBVal<DateTime>(reader["BirthDate"]),
				HireDate = DbHelper.ConvertFromDBVal<DateTime>(reader["HireDate"]),
				Address = DbHelper.ConvertFromDBVal<string>(reader["Address"]),
				City = DbHelper.ConvertFromDBVal<string>(reader["City"]),
				Region = DbHelper.ConvertFromDBVal<string>(reader["Region"]),
				PostalCode = DbHelper.ConvertFromDBVal<string>(reader["PostalCode"]),
				Country = DbHelper.ConvertFromDBVal<string>(reader["Country"]),
				HomePhone = DbHelper.ConvertFromDBVal<string>(reader["HomePhone"]),
				Extension = DbHelper.ConvertFromDBVal<string>(reader["Extension"]),
				Photo = DbHelper.ConvertFromDBVal<string>(reader["Photo"]),
				Notes = DbHelper.ConvertFromDBVal<string>(reader["Notes"]),
				ReportsTo = DbHelper.ConvertFromDBVal<int>(reader["ReportsTo"]),
				PhotoPath = DbHelper.ConvertFromDBVal<string>(reader["PhotoPath"])
           };

        /// <summary>
        /// Creates query parameters list from Employees object
        /// </summary>
        /// <param name="Employees">Employees.</param>
        /// <returns>Name value parameter list.</returns>
        private object[] Take(Employees data)
        {
            return new object[]  
            {
				"@EmployeeID", data.EmployeeID,
				"@LastName", data.LastName,
				"@FirstName", data.FirstName,
				"@Title", data.Title,
				"@TitleOfCourtesy", data.TitleOfCourtesy,
				"@BirthDate", data.BirthDate,
				"@HireDate", data.HireDate,
				"@Address", data.Address,
				"@City", data.City,
				"@Region", data.Region,
				"@PostalCode", data.PostalCode,
				"@Country", data.Country,
				"@HomePhone", data.HomePhone,
				"@Extension", data.Extension,
				"@Photo", data.Photo,
				"@Notes", data.Notes,
				"@ReportsTo", data.ReportsTo,
				"@PhotoPath", data.PhotoPath
            };
        }
    }
}
