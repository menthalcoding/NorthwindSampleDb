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
    public class TerritoriesController : Controller
    {    
        TerritoriesRepository TerritoriesRepo;

        public TerritoriesController()
        {
            TerritoriesRepo = new TerritoriesRepository();
        }

        //
        // GET: /Territories/
        
        public ActionResult Index()
        {
            return View(TerritoriesRepo.List(0, 1000));
        }
 
        //
        // GET: /Territories/Details?id=5
        public ActionResult Details(string TerritoryID)
        {
			Territories m = TerritoriesRepo.Get(TerritoryID);
			m.EmployeesTerritoriesList = TerritoriesRepo.GetEmployeesTerritoriesList(TerritoryID);

			return View (m);
        }
 
        //
        // GET: /Territories/Create
        public ActionResult Create()
        {
			ViewBag.RegionList = new RegionRepository().List(0, 1000).ToSelectList<Region>(nameof(Region.RegionID), nameof(Region.RegionDescription));

            return View(new Models.Territories());
        }
 
        //
        // POST: /Territories/Create
        [HttpPost]
        public ActionResult Create(Models.Territories model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    //if (model.OrderId < 1 || TerritoriesRepo.Get(model.TerritoryID) == null)
                    //{
                        TerritoriesRepo.Insert(model);

                        return RedirectToAction("Index");
                    //}
                    //else
                    //{
                    //    }

				ViewBag.RegionList = new RegionRepository().List(0, 1000).ToSelectList<Region>(nameof(Region.RegionID), nameof(Region.RegionDescription));
                return View(model);
            }
            catch
            {
                return View(model);
            }
        }
 
        //
        // GET: /Territories/Edit?id=5
        public ActionResult Edit(@funcParams)
        {
            try
            {
				ViewBag.RegionList = new RegionRepository().List(0, 1000).ToSelectList<Region>(nameof(Region.RegionID), nameof(Region.RegionDescription));

                return View(TerritoriesRepo.Get(TerritoryID));
            }
            catch
            {
                return View(new Models.Territories());
            }
        }
 
        //
        // POST: /Territories/Edit?id=5
        [HttpPost]
        public ActionResult Edit(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    TerritoriesRepo.Update(model);
 
                    return RedirectToAction("Index");
                }
                else
                {
					ViewBag.RegionList = new RegionRepository().List(0, 1000).ToSelectList<Region>(nameof(Region.RegionID), nameof(Region.RegionDescription));
                    return View(model);
                }
            }
            catch
            {
                return View(model);
            }
        }
 
        //
        // GET: /Territories/Delete?id=5
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
        // POST: /Territories/Delete?id=5
        [HttpPost]
        public ActionResult Delete(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                TerritoriesRepo.Delete(TerritoryID);
 
                return RedirectToAction("Index");
            }
            catch
            {
                return View(model);
            }
        }
    }
}