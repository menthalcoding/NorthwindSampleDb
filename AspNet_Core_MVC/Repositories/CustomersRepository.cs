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
    internal class CustomersRepository //: IRepository_Customers
    {
        #region ICustomersDao Members

        internal void Insert(Customers data)
        {
            string sql = @"exec sp_Customers_Insert @CustomerID, @CompanyName, @ContactName, @ContactTitle, @Address, @City, @Region, @PostalCode, @Country, @Phone, @Fax"; 

            Db.Insert(sql, Take(data)); 
        }

        internal Customers Get(string CustomerID)
        {
            string sql = @"exec sp_Customers_Select @CustomerID";

            return Db.Read(sql, Make, new object[] { "@CustomerID", CustomerID }); 
        }

		internal List<Order> GetOrderList(string CustomerID)
		{
			string sql = @"exec sp_Customers_Order_List @CustomerID";

			return Db.ReadList(sql, OrderRepository.Make, new object[] { "@CustomerID", CustomerID });
		}

		internal List<CustomersCustomersDemo> GetCustomersCustomersDemoList(string CustomerID)
		{
			string sql = @"exec sp_Customers_CustomersCustomersDemo_List @CustomerID";

			return Db.ReadList(sql, CustomersCustomersDemoRepository.Make, new object[] { "@CustomerID", CustomerID });
		}

        internal List<Customers> List(int startIndex, int itemCount)
        {
            string sql = @"exec sp_Customers_List @startIndex, @itemCount";

            return Db.ReadList(sql, Make, new object[] { "@startIndex", startIndex, "@itemCount", itemCount }); 
        }

        internal void Update(Customers data)
        {
            string sql = @"exec sp_Customers_Update @CustomerID, @CompanyName, @ContactName, @ContactTitle, @Address, @City, @Region, @PostalCode, @Country, @Phone, @Fax";

            Db.Update(sql, Take(data));
        }

        internal void Delete(string CustomerID)
        {
            string sql = @"exec sp_Customers_Delete @CustomerID";

            Db.Delete(sql, new object[] { "@CustomerID", CustomerID });
        }

        #endregion
        
        /// <summary>
        /// Creates a Customers object based on DataReader.
        /// </summary>
        internal static Func<IDataReader, Customers> Make = reader =>
           new Customers
           {
				CustomerID = DbHelper.ConvertFromDBVal<string>(reader["CustomerID"]),
				CompanyName = DbHelper.ConvertFromDBVal<string>(reader["CompanyName"]),
				ContactName = DbHelper.ConvertFromDBVal<string>(reader["ContactName"]),
				ContactTitle = DbHelper.ConvertFromDBVal<string>(reader["ContactTitle"]),
				Address = DbHelper.ConvertFromDBVal<string>(reader["Address"]),
				City = DbHelper.ConvertFromDBVal<string>(reader["City"]),
				Region = DbHelper.ConvertFromDBVal<string>(reader["Region"]),
				PostalCode = DbHelper.ConvertFromDBVal<string>(reader["PostalCode"]),
				Country = DbHelper.ConvertFromDBVal<string>(reader["Country"]),
				Phone = DbHelper.ConvertFromDBVal<string>(reader["Phone"]),
				Fax = DbHelper.ConvertFromDBVal<string>(reader["Fax"])
           };

        /// <summary>
        /// Creates query parameters list from Customers object
        /// </summary>
        /// <param name="Customers">Customers.</param>
        /// <returns>Name value parameter list.</returns>
        private object[] Take(Customers data)
        {
            return new object[]  
            {
				"@CustomerID", data.CustomerID,
				"@CompanyName", data.CompanyName,
				"@ContactName", data.ContactName,
				"@ContactTitle", data.ContactTitle,
				"@Address", data.Address,
				"@City", data.City,
				"@Region", data.Region,
				"@PostalCode", data.PostalCode,
				"@Country", data.Country,
				"@Phone", data.Phone,
				"@Fax", data.Fax
            };
        }
    }
}
