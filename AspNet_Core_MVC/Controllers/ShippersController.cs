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
    public class ShippersController : Controller
    {    
        ShippersRepository ShippersRepo;

        public ShippersController()
        {
            ShippersRepo = new ShippersRepository();
        }

        //
        // GET: /Shippers/
        
        public ActionResult Index()
        {
            return View(ShippersRepo.List(0, 1000));
        }
 
        //
        // GET: /Shippers/Details?id=5
        public ActionResult Details(int ShipperId)
        {
			Shippers m = ShippersRepo.Get(ShipperId);
			m.OrderList = ShippersRepo.GetOrderList(ShipperId);

			return View (m);
        }
 
        //
        // GET: /Shippers/Create
        public ActionResult Create()
        {

            return View(new Models.Shippers());
        }
 
        //
        // POST: /Shippers/Create
        [HttpPost]
        public ActionResult Create(Models.Shippers model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    //if (model.OrderId < 1 || ShippersRepo.Get(model.ShipperId) == null)
                    //{
                        ShippersRepo.Insert(model);

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
        // GET: /Shippers/Edit?id=5
        public ActionResult Edit(@funcParams)
        {
            try
            {

                return View(ShippersRepo.Get(ShipperId));
            }
            catch
            {
                return View(new Models.Shippers());
            }
        }
 
        //
        // POST: /Shippers/Edit?id=5
        [HttpPost]
        public ActionResult Edit(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    ShippersRepo.Update(model);
 
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
        // GET: /Shippers/Delete?id=5
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
        // POST: /Shippers/Delete?id=5
        [HttpPost]
        public ActionResult Delete(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                ShippersRepo.Delete(ShipperId);
 
                return RedirectToAction("Index");
            }
            catch
            {
                return View(model);
            }
        }
    }
}