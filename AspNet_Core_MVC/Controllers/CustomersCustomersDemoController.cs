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
    public class CustomersCustomersDemoController : Controller
    {    
        CustomersCustomersDemoRepository CustomersCustomersDemoRepo;

        public CustomersCustomersDemoController()
        {
            CustomersCustomersDemoRepo = new CustomersCustomersDemoRepository();
        }

        //
        // GET: /CustomersCustomersDemo/
        
        public ActionResult Index()
        {
            return View(CustomersCustomersDemoRepo.List(0, 1000));
        }
 
        //
        // GET: /CustomersCustomersDemo/Details?id=5
        public ActionResult Details(string CustomerID, string CustomersTypeId)
        {
			CustomersCustomersDemo m = CustomersCustomersDemoRepo.Get(CustomerID, CustomersTypeId);

			return View (m);
        }
 
        //
        // GET: /CustomersCustomersDemo/Create
        public ActionResult Create()
        {
			ViewBag.CustomersList = new CustomersRepository().List(0, 1000).ToSelectList<Customers>(nameof(Customers.CustomerID), nameof(Customers.CompanyName));
			ViewBag.CustomersDemographicsList = new CustomersDemographicsRepository().List(0, 1000).ToSelectList<CustomersDemographics>(nameof(CustomersDemographics.CustomersTypeId), nameof(CustomersDemographics.CustomersTypeId));

            return View(new Models.CustomersCustomersDemo());
        }
 
        //
        // POST: /CustomersCustomersDemo/Create
        [HttpPost]
        public ActionResult Create(Models.CustomersCustomersDemo model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    //if (model.OrderId < 1 || CustomersCustomersDemoRepo.Get(model.CustomerID, model.CustomersTypeId) == null)
                    //{
                        CustomersCustomersDemoRepo.Insert(model);

                        return RedirectToAction("Index");
                    //}
                    //else
                    //{
                    //    }

}

				ViewBag.CustomersList = new CustomersRepository().List(0, 1000).ToSelectList<Customers>(nameof(Customers.CustomerID), nameof(Customers.CompanyName));
				ViewBag.CustomersDemographicsList = new CustomersDemographicsRepository().List(0, 1000).ToSelectList<CustomersDemographics>(nameof(CustomersDemographics.CustomersTypeId), nameof(CustomersDemographics.CustomersTypeId));
                return View(model);
            }
            catch
            {
                return View(model);
            }
        }
 
        //
        // GET: /CustomersCustomersDemo/Edit?id=5
        public ActionResult Edit(@funcParams)
        {
            try
            {
				ViewBag.CustomersList = new CustomersRepository().List(0, 1000).ToSelectList<Customers>(nameof(Customers.CustomerID), nameof(Customers.CompanyName));
				ViewBag.CustomersDemographicsList = new CustomersDemographicsRepository().List(0, 1000).ToSelectList<CustomersDemographics>(nameof(CustomersDemographics.CustomersTypeId), nameof(CustomersDemographics.CustomersTypeId));

                return View(CustomersCustomersDemoRepo.Get(CustomerID, CustomersTypeId));
            }
            catch
            {
                return View(new Models.CustomersCustomersDemo());
            }
        }
 
        //
        // POST: /CustomersCustomersDemo/Edit?id=5
        [HttpPost]
        public ActionResult Edit(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    CustomersCustomersDemoRepo.Update(model);
 
                    return RedirectToAction("Index");
                }
                else
                {
					ViewBag.CustomersList = new CustomersRepository().List(0, 1000).ToSelectList<Customers>(nameof(Customers.CustomerID), nameof(Customers.CompanyName));
					ViewBag.CustomersDemographicsList = new CustomersDemographicsRepository().List(0, 1000).ToSelectList<CustomersDemographics>(nameof(CustomersDemographics.CustomersTypeId), nameof(CustomersDemographics.CustomersTypeId));
                    return View(model);
                }
            }
            catch
            {
                return View(model);
            }
        }
 
        //
        // GET: /CustomersCustomersDemo/Delete?id=5
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
        // POST: /CustomersCustomersDemo/Delete?id=5
        [HttpPost]
        public ActionResult Delete(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                CustomersCustomersDemoRepo.Delete(CustomerID, CustomersTypeId);
 
                return RedirectToAction("Index");
            }
            catch
            {
                return View(model);
            }
        }
    }
}