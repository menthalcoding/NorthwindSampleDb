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
    internal class OrderDetailsRepository //: IRepository_OrderDetails
    {
        #region IOrderDetailsDao Members

        internal void Insert(OrderDetails data)
        {
            string sql = @"exec sp_OrderDetails_Insert @OrderId, @ProductId, @UnitPrice, @Quantity, @Discount"; 

            Db.Insert(sql, Take(data)); 
        }

        internal OrderDetails Get(int OrderId, int ProductId)
        {
            string sql = @"exec sp_OrderDetails_Select @OrderId, @ProductId";

            return Db.Read(sql, Make, new object[] { "@OrderId", OrderId, "@ProductId", ProductId }); 
        }

        internal List<OrderDetails> List(int startIndex, int itemCount)
        {
            string sql = @"exec sp_OrderDetails_List @startIndex, @itemCount";

            return Db.ReadList(sql, Make, new object[] { "@startIndex", startIndex, "@itemCount", itemCount }); 
        }

        internal void Update(OrderDetails data)
        {
            string sql = @"exec sp_OrderDetails_Update @OrderId, @ProductId, @UnitPrice, @Quantity, @Discount";

            Db.Update(sql, Take(data));
        }

        internal void Delete(int OrderId, int ProductId)
        {
            string sql = @"exec sp_OrderDetails_Delete @OrderId, @ProductId";

            Db.Delete(sql, new object[] { "@OrderId", OrderId, "@ProductId", ProductId });
        }

        #endregion
        
        /// <summary>
        /// Creates a OrderDetails object based on DataReader.
        /// </summary>
        internal static Func<IDataReader, OrderDetails> Make = reader =>
           new OrderDetails
           {
				OrderId = DbHelper.ConvertFromDBVal<int>(reader["OrderId"]),
				ProductId = DbHelper.ConvertFromDBVal<int>(reader["ProductId"]),
				UnitPrice = DbHelper.ConvertFromDBVal<double>(reader["UnitPrice"]),
				Quantity = DbHelper.ConvertFromDBVal<short>(reader["Quantity"]),
				Discount = DbHelper.ConvertFromDBVal<double>(reader["Discount"])
           };

        /// <summary>
        /// Creates query parameters list from OrderDetails object
        /// </summary>
        /// <param name="OrderDetails">OrderDetails.</param>
        /// <returns>Name value parameter list.</returns>
        private object[] Take(OrderDetails data)
        {
            return new object[]  
            {
				"@OrderId", data.OrderId,
				"@ProductId", data.ProductId,
				"@UnitPrice", data.UnitPrice,
				"@Quantity", data.Quantity,
				"@Discount", data.Discount
            };
        }
    }
}
