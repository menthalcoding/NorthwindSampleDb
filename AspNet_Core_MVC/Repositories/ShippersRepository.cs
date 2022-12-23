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
    internal class ShippersRepository //: IRepository_Shippers
    {
        #region IShippersDao Members

        internal void Insert(Shippers data)
        {
            string sql = @"exec sp_Shippers_Insert @ShipperId, @CompanyName, @Phone"; 

            Db.Insert(sql, Take(data)); 
        }

        internal Shippers Get(int ShipperId)
        {
            string sql = @"exec sp_Shippers_Select @ShipperId";

            return Db.Read(sql, Make, new object[] { "@ShipperId", ShipperId }); 
        }

		internal List<Order> GetOrderList(int ShipperId)
		{
			string sql = @"exec sp_Shippers_Order_List @ShipperId";

			return Db.ReadList(sql, OrderRepository.Make, new object[] { "@ShipperId", ShipperId });
		}

        internal List<Shippers> List(int startIndex, int itemCount)
        {
            string sql = @"exec sp_Shippers_List @startIndex, @itemCount";

            return Db.ReadList(sql, Make, new object[] { "@startIndex", startIndex, "@itemCount", itemCount }); 
        }

        internal void Update(Shippers data)
        {
            string sql = @"exec sp_Shippers_Update @ShipperId, @CompanyName, @Phone";

            Db.Update(sql, Take(data));
        }

        internal void Delete(int ShipperId)
        {
            string sql = @"exec sp_Shippers_Delete @ShipperId";

            Db.Delete(sql, new object[] { "@ShipperId", ShipperId });
        }

        #endregion
        
        /// <summary>
        /// Creates a Shippers object based on DataReader.
        /// </summary>
        internal static Func<IDataReader, Shippers> Make = reader =>
           new Shippers
           {
				ShipperId = DbHelper.ConvertFromDBVal<int>(reader["ShipperId"]),
				CompanyName = DbHelper.ConvertFromDBVal<string>(reader["CompanyName"]),
				Phone = DbHelper.ConvertFromDBVal<string>(reader["Phone"])
           };

        /// <summary>
        /// Creates query parameters list from Shippers object
        /// </summary>
        /// <param name="Shippers">Shippers.</param>
        /// <returns>Name value parameter list.</returns>
        private object[] Take(Shippers data)
        {
            return new object[]  
            {
				"@ShipperId", data.ShipperId,
				"@CompanyName", data.CompanyName,
				"@Phone", data.Phone
            };
        }
    }
}
