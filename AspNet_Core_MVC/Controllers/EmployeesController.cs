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
    public class EmployeesController : Controller
    {    
        EmployeesRepository EmployeesRepo;

        public EmployeesController()
        {
            EmployeesRepo = new EmployeesRepository();
        }

        //
        // GET: /Employees/
        
        public ActionResult Index()
        {
            return View(EmployeesRepo.List(0, 1000));
        }
 
        //
        // GET: /Employees/Details?id=5
        public ActionResult Details(int EmployeeID)
        {
			Employees m = EmployeesRepo.Get(EmployeeID);
			m.OrderList = EmployeesRepo.GetOrderList(EmployeeID);
			m.EmployeesTerritoriesList = EmployeesRepo.GetEmployeesTerritoriesList(EmployeeID);

			return View (m);
        }
 
        //
        // GET: /Employees/Create
        public ActionResult Create()
        {

            return View(new Models.Employees());
        }
 
        //
        // POST: /Employees/Create
        [HttpPost]
        public ActionResult Create(Models.Employees model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    //if (model.OrderId < 1 || EmployeesRepo.Get(model.EmployeeID) == null)
                    //{
                        EmployeesRepo.Insert(model);

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
        // GET: /Employees/Edit?id=5
        public ActionResult Edit(@funcParams)
        {
            try
            {

                return View(EmployeesRepo.Get(EmployeeID));
            }
            catch
            {
                return View(new Models.Employees());
            }
        }
 
        //
        // POST: /Employees/Edit?id=5
        [HttpPost]
        public ActionResult Edit(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    EmployeesRepo.Update(model);
 
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
        // GET: /Employees/Delete?id=5
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
        // POST: /Employees/Delete?id=5
        [HttpPost]
        public ActionResult Delete(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                EmployeesRepo.Delete(EmployeeID);
 
                return RedirectToAction("Index");
            }
            catch
            {
                return View(model);
            }
        }
    }
}