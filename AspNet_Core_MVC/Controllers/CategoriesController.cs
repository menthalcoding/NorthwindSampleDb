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
    public class CategoriesController : Controller
    {    
        CategoriesRepository CategoriesRepo;

        public CategoriesController()
        {
            CategoriesRepo = new CategoriesRepository();
        }

        //
        // GET: /Categories/
        
        public ActionResult Index()
        {
            return View(CategoriesRepo.List(0, 1000));
        }
 
        //
        // GET: /Categories/Details?id=5
        public ActionResult Details(int CategoryID)
        {
			Categories m = CategoriesRepo.Get(CategoryID);
			m.ProductsList = CategoriesRepo.GetProductsList(CategoryID);

			return View (m);
        }
 
        //
        // GET: /Categories/Create
        public ActionResult Create()
        {

            return View(new Models.Categories());
        }
 
        //
        // POST: /Categories/Create
        [HttpPost]
        public ActionResult Create(Models.Categories model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    //if (model.OrderId < 1 || CategoriesRepo.Get(model.CategoryID) == null)
                    //{
                        CategoriesRepo.Insert(model);

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
        // GET: /Categories/Edit?id=5
        public ActionResult Edit(@funcParams)
        {
            try
            {

                return View(CategoriesRepo.Get(CategoryID));
            }
            catch
            {
                return View(new Models.Categories());
            }
        }
 
        //
        // POST: /Categories/Edit?id=5
        [HttpPost]
        public ActionResult Edit(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    CategoriesRepo.Update(model);
 
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
        // GET: /Categories/Delete?id=5
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
        // POST: /Categories/Delete?id=5
        [HttpPost]
        public ActionResult Delete(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                CategoriesRepo.Delete(CategoryID);
 
                return RedirectToAction("Index");
            }
            catch
            {
                return View(model);
            }
        }
    }
}