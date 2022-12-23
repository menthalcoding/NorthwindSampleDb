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
    public class EmployeesTerritoriesController : Controller
    {    
        EmployeesTerritoriesRepository EmployeesTerritoriesRepo;

        public EmployeesTerritoriesController()
        {
            EmployeesTerritoriesRepo = new EmployeesTerritoriesRepository();
        }

        //
        // GET: /EmployeesTerritories/
        
        public ActionResult Index()
        {
            return View(EmployeesTerritoriesRepo.List(0, 1000));
        }
 
        //
        // GET: /EmployeesTerritories/Details?id=5
        public ActionResult Details(int EmpleymontId, string TerritoryID)
        {
			EmployeesTerritories m = EmployeesTerritoriesRepo.Get(EmpleymontId, TerritoryID);

			return View (m);
        }
 
        //
        // GET: /EmployeesTerritories/Create
        public ActionResult Create()
        {
			ViewBag.EmployeesList = new EmployeesRepository().List(0, 1000).ToSelectList<Employees>(nameof(Employees.EmployeeID), nameof(Employees.Title));
			ViewBag.TerritoriesList = new TerritoriesRepository().List(0, 1000).ToSelectList<Territories>(nameof(Territories.TerritoryID), nameof(Territories.TerritoryID));

            return View(new Models.EmployeesTerritories());
        }
 
        //
        // POST: /EmployeesTerritories/Create
        [HttpPost]
        public ActionResult Create(Models.EmployeesTerritories model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    //if (model.OrderId < 1 || EmployeesTerritoriesRepo.Get(model.EmpleymontId, model.TerritoryID) == null)
                    //{
                        EmployeesTerritoriesRepo.Insert(model);

                        return RedirectToAction("Index");
                    //}
                    //else
                    //{
                    //    }

}

				ViewBag.EmployeesList = new EmployeesRepository().List(0, 1000).ToSelectList<Employees>(nameof(Employees.EmployeeID), nameof(Employees.Title));
				ViewBag.TerritoriesList = new TerritoriesRepository().List(0, 1000).ToSelectList<Territories>(nameof(Territories.TerritoryID), nameof(Territories.TerritoryID));
                return View(model);
            }
            catch
            {
                return View(model);
            }
        }
 
        //
        // GET: /EmployeesTerritories/Edit?id=5
        public ActionResult Edit(@funcParams)
        {
            try
            {
				ViewBag.EmployeesList = new EmployeesRepository().List(0, 1000).ToSelectList<Employees>(nameof(Employees.EmployeeID), nameof(Employees.Title));
				ViewBag.TerritoriesList = new TerritoriesRepository().List(0, 1000).ToSelectList<Territories>(nameof(Territories.TerritoryID), nameof(Territories.TerritoryID));

                return View(EmployeesTerritoriesRepo.Get(EmpleymontId, TerritoryID));
            }
            catch
            {
                return View(new Models.EmployeesTerritories());
            }
        }
 
        //
        // POST: /EmployeesTerritories/Edit?id=5
        [HttpPost]
        public ActionResult Edit(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    EmployeesTerritoriesRepo.Update(model);
 
                    return RedirectToAction("Index");
                }
                else
                {
					ViewBag.EmployeesList = new EmployeesRepository().List(0, 1000).ToSelectList<Employees>(nameof(Employees.EmployeeID), nameof(Employees.Title));
					ViewBag.TerritoriesList = new TerritoriesRepository().List(0, 1000).ToSelectList<Territories>(nameof(Territories.TerritoryID), nameof(Territories.TerritoryID));
                    return View(model);
                }
            }
            catch
            {
                return View(model);
            }
        }
 
        //
        // GET: /EmployeesTerritories/Delete?id=5
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
        // POST: /EmployeesTerritories/Delete?id=5
        [HttpPost]
        public ActionResult Delete(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                EmployeesTerritoriesRepo.Delete(EmpleymontId, TerritoryID);
 
                return RedirectToAction("Index");
            }
            catch
            {
                return View(model);
            }
        }
    }
}