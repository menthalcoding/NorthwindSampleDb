///////////////////////////////////////
// Author: Orhan Erdebil
// CreatedDate: 23 Aralık 2022 Cuma
// License terms are specified in the "license.txt" file in the root directory.
///////////////////////////////////////

using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Microsoft.AspNetCore.Mvc;
//using System.Web.Mvc;
using OnlineCoding.Code.Helper;
using NorthwindSampleDb.Models;
using NorthwindSampleDb.Repositories;

namespace NorthwindSampleDb.Controllers
{
    public class ProductsController : Controller
    {    
        ProductsRepository ProductsRepo;

        public ProductsController()
        {
            ProductsRepo = new ProductsRepository();
        }

        //
        // GET: /Products/
        
        public ActionResult Index()
        {
            return View(ProductsRepo.List(0, 1000));
        }
 
        //
        // GET: /Products/Details?id=5
        public ActionResult Details(int ProductId)
        {
			Products m = ProductsRepo.Get(ProductId);
			m.OrderDetailsList = ProductsRepo.GetOrderDetailsList(ProductId);

			return View (m);
        }
 
        //
        // GET: /Products/Create
        public ActionResult Create()
        {
			ViewBag.CategoriesList = new CategoriesRepository().List(0, 1000).ToSelectList<Categories>(nameof(Categories.CategoryID), nameof(Categories.CategoryName));
			ViewBag.SuppliersList = new SuppliersRepository().List(0, 1000).ToSelectList<Suppliers>(nameof(Suppliers.SupplierID), nameof(Suppliers.CompanyName));

            return View(new Models.Products());
        }
 
        //
        // POST: /Products/Create
        [HttpPost]
        public ActionResult Create(Models.Products model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    //if (model.OrderId < 1 || ProductsRepo.Get(model.ProductId) == null)
                    //{
                        ProductsRepo.Insert(model);

                        return RedirectToAction("Index");
                    //}
                    //else
                    //{
                    //    }

				ViewBag.CategoriesList = new CategoriesRepository().List(0, 1000).ToSelectList<Categories>(nameof(Categories.CategoryID), nameof(Categories.CategoryName));
				ViewBag.SuppliersList = new SuppliersRepository().List(0, 1000).ToSelectList<Suppliers>(nameof(Suppliers.SupplierID), nameof(Suppliers.CompanyName));
                return View(model);
            }
            catch
            {
                return View(model);
            }
        }
 
        //
        // GET: /Products/Edit?id=5
        public ActionResult Edit(@funcParams)
        {
            try
            {
				ViewBag.CategoriesList = new CategoriesRepository().List(0, 1000).ToSelectList<Categories>(nameof(Categories.CategoryID), nameof(Categories.CategoryName));
				ViewBag.SuppliersList = new SuppliersRepository().List(0, 1000).ToSelectList<Suppliers>(nameof(Suppliers.SupplierID), nameof(Suppliers.CompanyName));

                return View(ProductsRepo.Get(ProductId));
            }
            catch
            {
                return View(new Models.Products());
            }
        }
 
        //
        // POST: /Products/Edit?id=5
        [HttpPost]
        public ActionResult Edit(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    ProductsRepo.Update(model);
 
                    return RedirectToAction("Index");
                }
                else
                {
					ViewBag.CategoriesList = new CategoriesRepository().List(0, 1000).ToSelectList<Categories>(nameof(Categories.CategoryID), nameof(Categories.CategoryName));
					ViewBag.SuppliersList = new SuppliersRepository().List(0, 1000).ToSelectList<Suppliers>(nameof(Suppliers.SupplierID), nameof(Suppliers.CompanyName));
                    return View(model);
                }
            }
            catch
            {
                return View(model);
            }
        }
 
        //
        // GET: /Products/Delete?id=5
        public ActionResult Delete(@funcParams)
        {
            try
            {
                return View(@Html.Raw(Model.Name)Repo.Get(@inputParams));
            }
            catch
            {
                return View();
            }
        }
 
        //
        // POST: /Products/Delete?id=5
        [HttpPost]
        public ActionResult Delete(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                ProductsRepo.Delete(ProductId);
 
                return RedirectToAction("Index");
            }
            catch
            {
                return View(model);
            }
        }
    }
}