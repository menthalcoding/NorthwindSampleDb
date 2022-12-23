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
    internal class SuppliersRepository //: IRepository_Suppliers
    {
        #region ISuppliersDao Members

        internal void Insert(Suppliers data)
        {
            string sql = @"exec sp_Suppliers_Insert @SupplierID, @CompanyName, @ContactName, @ContactTitle, @Address, @City, @Region, @PostalCode, @Country, @Phone, @Fax, @HomePage"; 

            Db.Insert(sql, Take(data)); 
        }

        internal Suppliers Get(int SupplierID)
        {
            string sql = @"exec sp_Suppliers_Select @SupplierID";

            return Db.Read(sql, Make, new object[] { "@SupplierID", SupplierID }); 
        }

		internal List<Products> GetProductsList(int SupplierID)
		{
			string sql = @"exec sp_Suppliers_Products_List @SupplierID";

			return Db.ReadList(sql, ProductsRepository.Make, new object[] { "@SupplierID", SupplierID });
		}

        internal List<Suppliers> List(int startIndex, int itemCount)
        {
            string sql = @"exec sp_Suppliers_List @startIndex, @itemCount";

            return Db.ReadList(sql, Make, new object[] { "@startIndex", startIndex, "@itemCount", itemCount }); 
        }

        internal void Update(Suppliers data)
        {
            string sql = @"exec sp_Suppliers_Update @SupplierID, @CompanyName, @ContactName, @ContactTitle, @Address, @City, @Region, @PostalCode, @Country, @Phone, @Fax, @HomePage";

            Db.Update(sql, Take(data));
        }

        internal void Delete(int SupplierID)
        {
            string sql = @"exec sp_Suppliers_Delete @SupplierID";

            Db.Delete(sql, new object[] { "@SupplierID", SupplierID });
        }

        #endregion
        
        /// <summary>
        /// Creates a Suppliers object based on DataReader.
        /// </summary>
        internal static Func<IDataReader, Suppliers> Make = reader =>
           new Suppliers
           {
				SupplierID = DbHelper.ConvertFromDBVal<int>(reader["SupplierID"]),
				CompanyName = DbHelper.ConvertFromDBVal<string>(reader["CompanyName"]),
				ContactName = DbHelper.ConvertFromDBVal<string>(reader["ContactName"]),
				ContactTitle = DbHelper.ConvertFromDBVal<string>(reader["ContactTitle"]),
				Address = DbHelper.ConvertFromDBVal<string>(reader["Address"]),
				City = DbHelper.ConvertFromDBVal<string>(reader["City"]),
				Region = DbHelper.ConvertFromDBVal<string>(reader["Region"]),
				PostalCode = DbHelper.ConvertFromDBVal<string>(reader["PostalCode"]),
				Country = DbHelper.ConvertFromDBVal<string>(reader["Country"]),
				Phone = DbHelper.ConvertFromDBVal<string>(reader["Phone"]),
				Fax = DbHelper.ConvertFromDBVal<string>(reader["Fax"]),
				HomePage = DbHelper.ConvertFromDBVal<string>(reader["HomePage"])
           };

        /// <summary>
        /// Creates query parameters list from Suppliers object
        /// </summary>
        /// <param name="Suppliers">Suppliers.</param>
        /// <returns>Name value parameter list.</returns>
        private object[] Take(Suppliers data)
        {
            return new object[]  
            {
				"@SupplierID", data.SupplierID,
				"@CompanyName", data.CompanyName,
				"@ContactName", data.ContactName,
				"@ContactTitle", data.ContactTitle,
				"@Address", data.Address,
				"@City", data.City,
				"@Region", data.Region,
				"@PostalCode", data.PostalCode,
				"@Country", data.Country,
				"@Phone", data.Phone,
				"@Fax", data.Fax,
				"@HomePage", data.HomePage
            };
        }
    }
}
