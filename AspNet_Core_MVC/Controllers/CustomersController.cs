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
    public class CustomersController : Controller
    {    
        CustomersRepository CustomersRepo;

        public CustomersController()
        {
            CustomersRepo = new CustomersRepository();
        }

        //
        // GET: /Customers/
        
        public ActionResult Index()
        {
            return View(CustomersRepo.List(0, 1000));
        }
 
        //
        // GET: /Customers/Details?id=5
        public ActionResult Details(string CustomerID)
        {
			Customers m = CustomersRepo.Get(CustomerID);
			m.OrderList = CustomersRepo.GetOrderList(CustomerID);
			m.CustomersCustomersDemoList = CustomersRepo.GetCustomersCustomersDemoList(CustomerID);

			return View (m);
        }
 
        //
        // GET: /Customers/Create
        public ActionResult Create()
        {

            return View(new Models.Customers());
        }
 
        //
        // POST: /Customers/Create
        [HttpPost]
        public ActionResult Create(Models.Customers model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    //if (model.OrderId < 1 || CustomersRepo.Get(model.CustomerID) == null)
                    //{
                        CustomersRepo.Insert(model);

                        return RedirectToAction("Index");
                    //}
                    //else
                    //{
                    //    }

                return View(model);
            }
            catch
            {
                return View(model);
            }
        }
 
        //
        // GET: /Customers/Edit?id=5
        public ActionResult Edit(@funcParams)
        {
            try
            {

                return View(CustomersRepo.Get(CustomerID));
            }
            catch
            {
                return View(new Models.Customers());
            }
        }
 
        //
        // POST: /Customers/Edit?id=5
        [HttpPost]
        public ActionResult Edit(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    CustomersRepo.Update(model);
 
                    return RedirectToAction("Index");
                }
                else
                {
                    return View(model);
                }
            }
            catch
            {
                return View(model);
            }
        }
 
        //
        // GET: /Customers/Delete?id=5
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
        // POST: /Customers/Delete?id=5
        [HttpPost]
        public ActionResult Delete(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                CustomersRepo.Delete(CustomerID);
 
                return RedirectToAction("Index");
            }
            catch
            {
                return View(model);
            }
        }
    }
}