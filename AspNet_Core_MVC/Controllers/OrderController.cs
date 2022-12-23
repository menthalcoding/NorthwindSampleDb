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
    public class OrderController : Controller
    {    
        OrderRepository OrderRepo;

        public OrderController()
        {
            OrderRepo = new OrderRepository();
        }

        //
        // GET: /Order/
        
        public ActionResult Index()
        {
            return View(OrderRepo.List(0, 1000));
        }
 
        //
        // GET: /Order/Details?id=5
        public ActionResult Details(int OrderId)
        {
			Order m = OrderRepo.Get(OrderId);
			m.OrderDetailsList = OrderRepo.GetOrderDetailsList(OrderId);

			return View (m);
        }
 
        //
        // GET: /Order/Create
        public ActionResult Create()
        {
			ViewBag.CustomersList = new CustomersRepository().List(0, 1000).ToSelectList<Customers>(nameof(Customers.CustomerID), nameof(Customers.CompanyName));
			ViewBag.EmployeesList = new EmployeesRepository().List(0, 1000).ToSelectList<Employees>(nameof(Employees.EmployeeID), nameof(Employees.Title));
			ViewBag.ShippersList = new ShippersRepository().List(0, 1000).ToSelectList<Shippers>(nameof(Shippers.ShipperId), nameof(Shippers.CompanyName));

            return View(new Models.Order());
        }
 
        //
        // POST: /Order/Create
        [HttpPost]
        public ActionResult Create(Models.Order model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    //if (model.OrderId < 1 || OrderRepo.Get(model.OrderId) == null)
                    //{
                        OrderRepo.Insert(model);

                        return RedirectToAction("Index");
                    //}
                    //else
                    //{
                    //    }

				ViewBag.CustomersList = new CustomersRepository().List(0, 1000).ToSelectList<Customers>(nameof(Customers.CustomerID), nameof(Customers.CompanyName));
				ViewBag.EmployeesList = new EmployeesRepository().List(0, 1000).ToSelectList<Employees>(nameof(Employees.EmployeeID), nameof(Employees.Title));
				ViewBag.ShippersList = new ShippersRepository().List(0, 1000).ToSelectList<Shippers>(nameof(Shippers.ShipperId), nameof(Shippers.CompanyName));
                return View(model);
            }
            catch
            {
                return View(model);
            }
        }
 
        //
        // GET: /Order/Edit?id=5
        public ActionResult Edit(@funcParams)
        {
            try
            {
				ViewBag.CustomersList = new CustomersRepository().List(0, 1000).ToSelectList<Customers>(nameof(Customers.CustomerID), nameof(Customers.CompanyName));
				ViewBag.EmployeesList = new EmployeesRepository().List(0, 1000).ToSelectList<Employees>(nameof(Employees.EmployeeID), nameof(Employees.Title));
				ViewBag.ShippersList = new ShippersRepository().List(0, 1000).ToSelectList<Shippers>(nameof(Shippers.ShipperId), nameof(Shippers.CompanyName));

                return View(OrderRepo.Get(OrderId));
            }
            catch
            {
                return View(new Models.Order());
            }
        }
 
        //
        // POST: /Order/Edit?id=5
        [HttpPost]
        public ActionResult Edit(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    OrderRepo.Update(model);
 
                    return RedirectToAction("Index");
                }
                else
                {
					ViewBag.CustomersList = new CustomersRepository().List(0, 1000).ToSelectList<Customers>(nameof(Customers.CustomerID), nameof(Customers.CompanyName));
					ViewBag.EmployeesList = new EmployeesRepository().List(0, 1000).ToSelectList<Employees>(nameof(Employees.EmployeeID), nameof(Employees.Title));
					ViewBag.ShippersList = new ShippersRepository().List(0, 1000).ToSelectList<Shippers>(nameof(Shippers.ShipperId), nameof(Shippers.CompanyName));
                    return View(model);
                }
            }
            catch
            {
                return View(model);
            }
        }
 
        //
        // GET: /Order/Delete?id=5
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
        // POST: /Order/Delete?id=5
        [HttpPost]
        public ActionResult Delete(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                OrderRepo.Delete(OrderId);
 
                return RedirectToAction("Index");
            }
            catch
            {
                return View(model);
            }
        }
    }
}