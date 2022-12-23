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
    public class OrderDetailsController : Controller
    {    
        OrderDetailsRepository OrderDetailsRepo;

        public OrderDetailsController()
        {
            OrderDetailsRepo = new OrderDetailsRepository();
        }

        //
        // GET: /OrderDetails/
        
        public ActionResult Index()
        {
            return View(OrderDetailsRepo.List(0, 1000));
        }
 
        //
        // GET: /OrderDetails/Details?id=5
        public ActionResult Details(int OrderId, int ProductId)
        {
			OrderDetails m = OrderDetailsRepo.Get(OrderId, ProductId);

			return View (m);
        }
 
        //
        // GET: /OrderDetails/Create
        public ActionResult Create()
        {
			ViewBag.OrderList = new OrderRepository().List(0, 1000).ToSelectList<Order>(nameof(Order.OrderId), nameof(Order.OrderId));
			ViewBag.ProductsList = new ProductsRepository().List(0, 1000).ToSelectList<Products>(nameof(Products.ProductId), nameof(Products.ProductName));

            return View(new Models.OrderDetails());
        }
 
        //
        // POST: /OrderDetails/Create
        [HttpPost]
        public ActionResult Create(Models.OrderDetails model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    //if (model.OrderId < 1 || OrderDetailsRepo.Get(model.OrderId, model.ProductId) == null)
                    //{
                        OrderDetailsRepo.Insert(model);

                        return RedirectToAction("Index");
                    //}
                    //else
                    //{
                    //    }

}

				ViewBag.OrderList = new OrderRepository().List(0, 1000).ToSelectList<Order>(nameof(Order.OrderId), nameof(Order.OrderId));
				ViewBag.ProductsList = new ProductsRepository().List(0, 1000).ToSelectList<Products>(nameof(Products.ProductId), nameof(Products.ProductName));
                return View(model);
            }
            catch
            {
                return View(model);
            }
        }
 
        //
        // GET: /OrderDetails/Edit?id=5
        public ActionResult Edit(@funcParams)
        {
            try
            {
				ViewBag.OrderList = new OrderRepository().List(0, 1000).ToSelectList<Order>(nameof(Order.OrderId), nameof(Order.OrderId));
				ViewBag.ProductsList = new ProductsRepository().List(0, 1000).ToSelectList<Products>(nameof(Products.ProductId), nameof(Products.ProductName));

                return View(OrderDetailsRepo.Get(OrderId, ProductId));
            }
            catch
            {
                return View(new Models.OrderDetails());
            }
        }
 
        //
        // POST: /OrderDetails/Edit?id=5
        [HttpPost]
        public ActionResult Edit(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    OrderDetailsRepo.Update(model);
 
                    return RedirectToAction("Index");
                }
                else
                {
					ViewBag.OrderList = new OrderRepository().List(0, 1000).ToSelectList<Order>(nameof(Order.OrderId), nameof(Order.OrderId));
					ViewBag.ProductsList = new ProductsRepository().List(0, 1000).ToSelectList<Products>(nameof(Products.ProductId), nameof(Products.ProductName));
                    return View(model);
                }
            }
            catch
            {
                return View(model);
            }
        }
 
        //
        // GET: /OrderDetails/Delete?id=5
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
        // POST: /OrderDetails/Delete?id=5
        [HttpPost]
        public ActionResult Delete(@funcParams, Models.@Html.Raw(Model.Name) model)
        {
            try
            {
                OrderDetailsRepo.Delete(OrderId, ProductId);
 
                return RedirectToAction("Index");
            }
            catch
            {
                return View(model);
            }
        }
    }
}