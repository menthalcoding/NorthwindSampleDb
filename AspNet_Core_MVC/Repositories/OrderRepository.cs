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
    internal class OrderRepository //: IRepository_Order
    {
        #region IOrderDao Members

        internal void Insert(Order data)
        {
            string sql = @"exec sp_Order_Insert @OrderId, @CustomerID, @EmployeeID, @OrderDate, @RequiredDate, @ShippedDate, @ShipName, @ShipVia, @Freight, @ShipAddress, @ShipCity, @ShipRegion, @ShipPostalCode, @ShipCountry"; 

            Db.Insert(sql, Take(data)); 
        }

        internal Order Get(int OrderId)
        {
            string sql = @"exec sp_Order_Select @OrderId";

            return Db.Read(sql, Make, new object[] { "@OrderId", OrderId }); 
        }

		internal List<OrderDetails> GetOrderDetailsList(int OrderId)
		{
			string sql = @"exec sp_Order_OrderDetails_List @OrderId";

			return Db.ReadList(sql, OrderDetailsRepository.Make, new object[] { "@OrderId", OrderId });
		}

        internal List<Order> List(int startIndex, int itemCount)
        {
            string sql = @"exec sp_Order_List @startIndex, @itemCount";

            return Db.ReadList(sql, Make, new object[] { "@startIndex", startIndex, "@itemCount", itemCount }); 
        }

        internal void Update(Order data)
        {
            string sql = @"exec sp_Order_Update @OrderId, @CustomerID, @EmployeeID, @OrderDate, @RequiredDate, @ShippedDate, @ShipName, @ShipVia, @Freight, @ShipAddress, @ShipCity, @ShipRegion, @ShipPostalCode, @ShipCountry";

            Db.Update(sql, Take(data));
        }

        internal void Delete(int OrderId)
        {
            string sql = @"exec sp_Order_Delete @OrderId";

            Db.Delete(sql, new object[] { "@OrderId", OrderId });
        }

        #endregion
        
        /// <summary>
        /// Creates a Order object based on DataReader.
        /// </summary>
        internal static Func<IDataReader, Order> Make = reader =>
           new Order
           {
				OrderId = DbHelper.ConvertFromDBVal<int>(reader["OrderId"]),
				CustomerID = DbHelper.ConvertFromDBVal<string>(reader["CustomerID"]),
				EmployeeID = DbHelper.ConvertFromDBVal<int>(reader["EmployeeID"]),
				OrderDate = DbHelper.ConvertFromDBVal<DateTime>(reader["OrderDate"]),
				RequiredDate = DbHelper.ConvertFromDBVal<DateTime>(reader["RequiredDate"]),
				ShippedDate = DbHelper.ConvertFromDBVal<DateTime>(reader["ShippedDate"]),
				ShipName = DbHelper.ConvertFromDBVal<string>(reader["ShipName"]),
				ShipVia = DbHelper.ConvertFromDBVal<int>(reader["ShipVia"]),
				Freight = DbHelper.ConvertFromDBVal<decimal>(reader["Freight"]),
				ShipAddress = DbHelper.ConvertFromDBVal<string>(reader["ShipAddress"]),
				ShipCity = DbHelper.ConvertFromDBVal<string>(reader["ShipCity"]),
				ShipRegion = DbHelper.ConvertFromDBVal<string>(reader["ShipRegion"]),
				ShipPostalCode = DbHelper.ConvertFromDBVal<string>(reader["ShipPostalCode"]),
				ShipCountry = DbHelper.ConvertFromDBVal<string>(reader["ShipCountry"])
           };

        /// <summary>
        /// Creates query parameters list from Order object
        /// </summary>
        /// <param name="Order">Order.</param>
        /// <returns>Name value parameter list.</returns>
        private object[] Take(Order data)
        {
            return new object[]  
            {
				"@OrderId", data.OrderId,
				"@CustomerID", data.CustomerID,
				"@EmployeeID", data.EmployeeID,
				"@OrderDate", data.OrderDate,
				"@RequiredDate", data.RequiredDate,
				"@ShippedDate", data.ShippedDate,
				"@ShipName", data.ShipName,
				"@ShipVia", data.ShipVia,
				"@Freight", data.Freight,
				"@ShipAddress", data.ShipAddress,
				"@ShipCity", data.ShipCity,
				"@ShipRegion", data.ShipRegion,
				"@ShipPostalCode", data.ShipPostalCode,
				"@ShipCountry", data.ShipCountry
            };
        }
    }
}
