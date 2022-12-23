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
    internal class CustomersDemographicsRepository //: IRepository_CustomersDemographics
    {
        #region ICustomersDemographicsDao Members

        internal void Insert(CustomersDemographics data)
        {
            string sql = @"exec sp_CustomersDemographics_Insert @CustomersTypeId, @CustomersDesc"; 

            Db.Insert(sql, Take(data)); 
        }

        internal CustomersDemographics Get(string CustomersTypeId)
        {
            string sql = @"exec sp_CustomersDemographics_Select @CustomersTypeId";

            return Db.Read(sql, Make, new object[] { "@CustomersTypeId", CustomersTypeId }); 
        }

		internal List<CustomersCustomersDemo> GetCustomersCustomersDemoList(string CustomersTypeId)
		{
			string sql = @"exec sp_CustomersDemographics_CustomersCustomersDemo_List @CustomersTypeId";

			return Db.ReadList(sql, CustomersCustomersDemoRepository.Make, new object[] { "@CustomersTypeId", CustomersTypeId });
		}

        internal List<CustomersDemographics> List(int startIndex, int itemCount)
        {
            string sql = @"exec sp_CustomersDemographics_List @startIndex, @itemCount";

            return Db.ReadList(sql, Make, new object[] { "@startIndex", startIndex, "@itemCount", itemCount }); 
        }

        internal void Update(CustomersDemographics data)
        {
            string sql = @"exec sp_CustomersDemographics_Update @CustomersTypeId, @CustomersDesc";

            Db.Update(sql, Take(data));
        }

        internal void Delete(string CustomersTypeId)
        {
            string sql = @"exec sp_CustomersDemographics_Delete @CustomersTypeId";

            Db.Delete(sql, new object[] { "@CustomersTypeId", CustomersTypeId });
        }

        #endregion
        
        /// <summary>
        /// Creates a CustomersDemographics object based on DataReader.
        /// </summary>
        internal static Func<IDataReader, CustomersDemographics> Make = reader =>
           new CustomersDemographics
           {
				CustomersTypeId = DbHelper.ConvertFromDBVal<string>(reader["CustomersTypeId"]),
				CustomersDesc = DbHelper.ConvertFromDBVal<string>(reader["CustomersDesc"])
           };

        /// <summary>
        /// Creates query parameters list from CustomersDemographics object
        /// </summary>
        /// <param name="CustomersDemographics">CustomersDemographics.</param>
        /// <returns>Name value parameter list.</returns>
        private object[] Take(CustomersDemographics data)
        {
            return new object[]  
            {
				"@CustomersTypeId", data.CustomersTypeId,
				"@CustomersDesc", data.CustomersDesc
            };
        }
    }
}
