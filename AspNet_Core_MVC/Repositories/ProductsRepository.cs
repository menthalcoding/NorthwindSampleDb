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
    internal class ProductsRepository //: IRepository_Products
    {
        #region IProductsDao Members

        internal void Insert(Products data)
        {
            string sql = @"exec sp_Products_Insert @ProductId, @ProductName, @SupplierID, @CategoryID, @QuantityPerUnit, @UnitPrice, @UnitsInStock, @UnitsOnOrder, @ReorderLevel, @Discontinued"; 

            Db.Insert(sql, Take(data)); 
        }

        internal Products Get(int ProductId)
        {
            string sql = @"exec sp_Products_Select @ProductId";

            return Db.Read(sql, Make, new object[] { "@ProductId", ProductId }); 
        }

		internal List<OrderDetails> GetOrderDetailsList(int ProductId)
		{
			string sql = @"exec sp_Products_OrderDetails_List @ProductId";

			return Db.ReadList(sql, OrderDetailsRepository.Make, new object[] { "@ProductId", ProductId });
		}

        internal List<Products> List(int startIndex, int itemCount)
        {
            string sql = @"exec sp_Products_List @startIndex, @itemCount";

            return Db.ReadList(sql, Make, new object[] { "@startIndex", startIndex, "@itemCount", itemCount }); 
        }

        internal void Update(Products data)
        {
            string sql = @"exec sp_Products_Update @ProductId, @ProductName, @SupplierID, @CategoryID, @QuantityPerUnit, @UnitPrice, @UnitsInStock, @UnitsOnOrder, @ReorderLevel, @Discontinued";

            Db.Update(sql, Take(data));
        }

        internal void Delete(int ProductId)
        {
            string sql = @"exec sp_Products_Delete @ProductId";

            Db.Delete(sql, new object[] { "@ProductId", ProductId });
        }

        #endregion
        
        /// <summary>
        /// Creates a Products object based on DataReader.
        /// </summary>
        internal static Func<IDataReader, Products> Make = reader =>
           new Products
           {
				ProductId = DbHelper.ConvertFromDBVal<int>(reader["ProductId"]),
				ProductName = DbHelper.ConvertFromDBVal<string>(reader["ProductName"]),
				SupplierID = DbHelper.ConvertFromDBVal<int>(reader["SupplierID"]),
				CategoryID = DbHelper.ConvertFromDBVal<int>(reader["CategoryID"]),
				QuantityPerUnit = DbHelper.ConvertFromDBVal<string>(reader["QuantityPerUnit"]),
				UnitPrice = DbHelper.ConvertFromDBVal<double>(reader["UnitPrice"]),
				UnitsInStock = DbHelper.ConvertFromDBVal<short>(reader["UnitsInStock"]),
				UnitsOnOrder = DbHelper.ConvertFromDBVal<short>(reader["UnitsOnOrder"]),
				ReorderLevel = DbHelper.ConvertFromDBVal<short>(reader["ReorderLevel"]),
				Discontinued = DbHelper.ConvertFromDBVal<bool>(reader["Discontinued"])
           };

        /// <summary>
        /// Creates query parameters list from Products object
        /// </summary>
        /// <param name="Products">Products.</param>
        /// <returns>Name value parameter list.</returns>
        private object[] Take(Products data)
        {
            return new object[]  
            {
				"@ProductId", data.ProductId,
				"@ProductName", data.ProductName,
				"@SupplierID", data.SupplierID,
				"@CategoryID", data.CategoryID,
				"@QuantityPerUnit", data.QuantityPerUnit,
				"@UnitPrice", data.UnitPrice,
				"@UnitsInStock", data.UnitsInStock,
				"@UnitsOnOrder", data.UnitsOnOrder,
				"@ReorderLevel", data.ReorderLevel,
				"@Discontinued", data.Discontinued
            };
        }
    }
}
