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
    internal class CategoriesRepository //: IRepository_Categories
    {
        #region ICategoriesDao Members

        internal void Insert(Categories data)
        {
            string sql = @"exec sp_Categories_Insert @CategoryID, @CategoryName, @Description, @Picture"; 

            Db.Insert(sql, Take(data)); 
        }

        internal Categories Get(int CategoryID)
        {
            string sql = @"exec sp_Categories_Select @CategoryID";

            return Db.Read(sql, Make, new object[] { "@CategoryID", CategoryID }); 
        }

		internal List<Products> GetProductsList(int CategoryID)
		{
			string sql = @"exec sp_Categories_Products_List @CategoryID";

			return Db.ReadList(sql, ProductsRepository.Make, new object[] { "@CategoryID", CategoryID });
		}

        internal List<Categories> List(int startIndex, int itemCount)
        {
            string sql = @"exec sp_Categories_List @startIndex, @itemCount";

            return Db.ReadList(sql, Make, new object[] { "@startIndex", startIndex, "@itemCount", itemCount }); 
        }

        internal void Update(Categories data)
        {
            string sql = @"exec sp_Categories_Update @CategoryID, @CategoryName, @Description, @Picture";

            Db.Update(sql, Take(data));
        }

        internal void Delete(int CategoryID)
        {
            string sql = @"exec sp_Categories_Delete @CategoryID";

            Db.Delete(sql, new object[] { "@CategoryID", CategoryID });
        }

        #endregion
        
        /// <summary>
        /// Creates a Categories object based on DataReader.
        /// </summary>
        internal static Func<IDataReader, Categories> Make = reader =>
           new Categories
           {
				CategoryID = DbHelper.ConvertFromDBVal<int>(reader["CategoryID"]),
				CategoryName = DbHelper.ConvertFromDBVal<string>(reader["CategoryName"]),
				Description = DbHelper.ConvertFromDBVal<string>(reader["Description"]),
				Picture = DbHelper.ConvertFromDBVal<string>(reader["Picture"])
           };

        /// <summary>
        /// Creates query parameters list from Categories object
        /// </summary>
        /// <param name="Categories">Categories.</param>
        /// <returns>Name value parameter list.</returns>
        private object[] Take(Categories data)
        {
            return new object[]  
            {
				"@CategoryID", data.CategoryID,
				"@CategoryName", data.CategoryName,
				"@Description", data.Description,
				"@Picture", data.Picture
            };
        }
    }
}
