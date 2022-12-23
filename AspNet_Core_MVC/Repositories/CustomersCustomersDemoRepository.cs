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
    internal class CustomersCustomersDemoRepository //: IRepository_CustomersCustomersDemo
    {
        #region ICustomersCustomersDemoDao Members

        internal void Insert(CustomersCustomersDemo data)
        {
            string sql = @"exec sp_CustomersCustomersDemo_Insert @CustomerID, @CustomersTypeId"; 

            Db.Insert(sql, Take(data)); 
        }

        internal CustomersCustomersDemo Get(string CustomerID, string CustomersTypeId)
        {
            string sql = @"exec sp_CustomersCustomersDemo_Select @CustomerID, @CustomersTypeId";

            return Db.Read(sql, Make, new object[] { "@CustomerID", CustomerID, "@CustomersTypeId", CustomersTypeId }); 
        }

        internal List<CustomersCustomersDemo> List(int startIndex, int itemCount)
        {
            string sql = @"exec sp_CustomersCustomersDemo_List @startIndex, @itemCount";

            return Db.ReadList(sql, Make, new object[] { "@startIndex", startIndex, "@itemCount", itemCount }); 
        }

        internal void Update(CustomersCustomersDemo data)
        {
            string sql = @"exec sp_CustomersCustomersDemo_Update @CustomerID, @CustomersTypeId";

            Db.Update(sql, Take(data));
        }

        internal void Delete(string CustomerID, string CustomersTypeId)
        {
            string sql = @"exec sp_CustomersCustomersDemo_Delete @CustomerID, @CustomersTypeId";

            Db.Delete(sql, new object[] { "@CustomerID", CustomerID, "@CustomersTypeId", CustomersTypeId });
        }

        #endregion
        
        /// <summary>
        /// Creates a CustomersCustomersDemo object based on DataReader.
        /// </summary>
        internal static Func<IDataReader, CustomersCustomersDemo> Make = reader =>
           new CustomersCustomersDemo
           {
				CustomerID = DbHelper.ConvertFromDBVal<string>(reader["CustomerID"]),
				CustomersTypeId = DbHelper.ConvertFromDBVal<string>(reader["CustomersTypeId"])
           };

        /// <summary>
        /// Creates query parameters list from CustomersCustomersDemo object
        /// </summary>
        /// <param name="CustomersCustomersDemo">CustomersCustomersDemo.</param>
        /// <returns>Name value parameter list.</returns>
        private object[] Take(CustomersCustomersDemo data)
        {
            return new object[]  
            {
				"@CustomerID", data.CustomerID,
				"@CustomersTypeId", data.CustomersTypeId
            };
        }
    }
}
