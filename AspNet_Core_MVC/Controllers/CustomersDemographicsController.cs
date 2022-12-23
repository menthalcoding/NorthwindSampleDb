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
    public class CustomersDemographicsController : Controller
    {    
        CustomersDemographicsRepository CustomersDemographicsRepo;

        public CustomersDemographicsController()
        {
            CustomersDemographicsRepo = new CustomersDemographicsRepository();
        }

        //
        // GET: /CustomersDemographics/
        
        public ActionResult Index()
        {
            return View(CustomersDemographicsRepo.List(0, 1000));
        }
 
        //
        // GET: /CustomersDemographics/Details?id=5
        public ActionResult Details(string CustomersTypeId)
        {
			CustomersDemographics m = CustomersDemographicsRepo.Get(CustomersTypeId);
			m.CustomersCustomersDemoList = CustomersDemographicsRepo.GetCustomersCustomersDemoList(CustomersTypeId);

			return View (m);
        }
 
        //
        // GET: /CustomersDemographics/Create
        public ActionResult Create()
        {

            return View(new Models.CustomersDemographics());
        }
 
        //
        // POST: /CustomersDemographics/Create
        [HttpPost]
        public ActionResult Create(Models.CustomersDemographics model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    //if (model.OrderId < 1 || CustomersDemographicsRepo.Get(model.CustomersTypeId) == null)
                    //{
                        CustomersDemographicsRepo.Insert(model);

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
        // GET: /CustomersDemographics/Edit?id=5
        public ActionResult Edit(@funcParams)
        {
            try
            {

                return View(CustomersDemographicsRepo.Get(CustomersTypeId));
            }
            catch
            {
                return View(new Models.CustomersDemographics());
            }
        }
 
        //
        // POST: /CustomersDemographics/Edit?id=5
        [HttpPost]
        public ActionResult Edit(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    CustomersDemographicsRepo.Update(model);
 
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
        // GET: /CustomersDemographics/Delete?id=5
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
        // POST: /CustomersDemographics/Delete?id=5
        [HttpPost]
        public ActionResult Delete(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                CustomersDemographicsRepo.Delete(CustomersTypeId);
 
                return RedirectToAction("Index");
            }
            catch
            {
                return View(model);
            }
        }
    }
}