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
    public class RegionController : Controller
    {    
        RegionRepository RegionRepo;

        public RegionController()
        {
            RegionRepo = new RegionRepository();
        }

        //
        // GET: /Region/
        
        public ActionResult Index()
        {
            return View(RegionRepo.List(0, 1000));
        }
 
        //
        // GET: /Region/Details?id=5
        public ActionResult Details(int RegionID)
        {
			Region m = RegionRepo.Get(RegionID);
			m.TerritoriesList = RegionRepo.GetTerritoriesList(RegionID);

			return View (m);
        }
 
        //
        // GET: /Region/Create
        public ActionResult Create()
        {

            return View(new Models.Region());
        }
 
        //
        // POST: /Region/Create
        [HttpPost]
        public ActionResult Create(Models.Region model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    //if (model.OrderId < 1 || RegionRepo.Get(model.RegionID) == null)
                    //{
                        RegionRepo.Insert(model);

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
        // GET: /Region/Edit?id=5
        public ActionResult Edit(@funcParams)
        {
            try
            {

                return View(RegionRepo.Get(RegionID));
            }
            catch
            {
                return View(new Models.Region());
            }
        }
 
        //
        // POST: /Region/Edit?id=5
        [HttpPost]
        public ActionResult Edit(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    RegionRepo.Update(model);
 
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
        // GET: /Region/Delete?id=5
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
        // POST: /Region/Delete?id=5
        [HttpPost]
        public ActionResult Delete(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                RegionRepo.Delete(RegionID);
 
                return RedirectToAction("Index");
            }
            catch
            {
                return View(model);
            }
        }
    }
}