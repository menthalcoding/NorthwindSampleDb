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
    public class SuppliersController : Controller
    {    
        SuppliersRepository SuppliersRepo;

        public SuppliersController()
        {
            SuppliersRepo = new SuppliersRepository();
        }

        //
        // GET: /Suppliers/
        
        public ActionResult Index()
        {
            return View(SuppliersRepo.List(0, 1000));
        }
 
        //
        // GET: /Suppliers/Details?id=5
        public ActionResult Details(int SupplierID)
        {
			Suppliers m = SuppliersRepo.Get(SupplierID);
			m.ProductsList = SuppliersRepo.GetProductsList(SupplierID);

			return View (m);
        }
 
        //
        // GET: /Suppliers/Create
        public ActionResult Create()
        {

            return View(new Models.Suppliers());
        }
 
        //
        // POST: /Suppliers/Create
        [HttpPost]
        public ActionResult Create(Models.Suppliers model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    //if (model.OrderId < 1 || SuppliersRepo.Get(model.SupplierID) == null)
                    //{
                        SuppliersRepo.Insert(model);

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
        // GET: /Suppliers/Edit?id=5
        public ActionResult Edit(@funcParams)
        {
            try
            {

                return View(SuppliersRepo.Get(SupplierID));
            }
            catch
            {
                return View(new Models.Suppliers());
            }
        }
 
        //
        // POST: /Suppliers/Edit?id=5
        [HttpPost]
        public ActionResult Edit(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    SuppliersRepo.Update(model);
 
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
        // GET: /Suppliers/Delete?id=5
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
        // POST: /Suppliers/Delete?id=5
        [HttpPost]
        public ActionResult Delete(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                SuppliersRepo.Delete(SupplierID);
 
                return RedirectToAction("Index");
            }
            catch
            {
                return View(model);
            }
        }
    }
}