///////////////////////////////////////
// Author: Orhan Erdebil
// CreatedDate: 23 Aralık 2022 Cuma
// License terms are specified in the "license.txt" file in the root directory.
///////////////////////////////////////

using Microsoft.AspNetCore.Mvc;
using System.ComponentModel.DataAnnotations;
using NorthwindSampleDb.Models;

namespace NorthwindSampleDb.WebApi.Models
{
    // Remove the fields you do not want to be served
    public class ProductsDTO
    {
		[Required(ErrorMessage = "Required")]
		public int ProductId { get; set; }

		[Required(ErrorMessage = "Required")]
		public string ProductName { get; set; }

		
		public int? SupplierID { get; set; }

		
		public int? CategoryID { get; set; }

		[MaxLength(20)]
		public string QuantityPerUnit { get; set; }

		
		public double? UnitPrice { get; set; }

		
		public short? UnitsInStock { get; set; }

		
		public short? UnitsOnOrder { get; set; }

		
		public short? ReorderLevel { get; set; }

		[Required(ErrorMessage = "Required")]
		public bool Discontinued { get; set; }

        public static ProductsDTO ToDataTransferObject(Products item)
        {
            if (item == null)
                return null;

            return new ProductsDTO
            {
				ProductId = item.ProductId,
				ProductName = item.ProductName,
				SupplierID = item.SupplierID,
				CategoryID = item.CategoryID,
				QuantityPerUnit = item.QuantityPerUnit,
				UnitPrice = item.UnitPrice,
				UnitsInStock = item.UnitsInStock,
				UnitsOnOrder = item.UnitsOnOrder,
				ReorderLevel = item.ReorderLevel,
				Discontinued = item.Discontinued,
            };
        }

        public static Products FromDataTransferObject(ProductsDTO item)
        {
            if (item == null)
                return null;

            return new Products
            {
				ProductId = item.ProductId,
				ProductName = item.ProductName,
				SupplierID = item.SupplierID,
				CategoryID = item.CategoryID,
				QuantityPerUnit = item.QuantityPerUnit,
				UnitPrice = item.UnitPrice,
				UnitsInStock = item.UnitsInStock,
				UnitsOnOrder = item.UnitsOnOrder,
				ReorderLevel = item.ReorderLevel,
				Discontinued = item.Discontinued,
            };
        }
    }
}