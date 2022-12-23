///////////////////////////////////////
// Author: Orhan Erdebil
// CreatedDate: 23 Aralık 2022 Cuma
// License terms are specified in the "license.txt" file in the root directory.
///////////////////////////////////////
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using NorthwindSampleDb.Repositories;
using NorthwindSampleDb.Models;
using NorthwindSampleDb.WebApi.Models;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace NorthwindSampleDb.WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
    public class OrderController : ControllerBase
    {
        OrderRepository _OrderRepo;

        public OrderController()
        {
            _OrderRepo = new OrderRepository();
        }

        // GET: api/<OrderController>
        
        [HttpGet]        
        public async Task<IActionResult> Get()
        {
            List<Order> items = _OrderRepo.List(0, 1000);
            if (items == null)
                return NotFound();

            return Ok(items.Select(i => OrderDTO.ToDataTransferObject(i)).ToList());
        }

        // GET api/<OrderController>/5
        [HttpGet("{OrderId}")]
        public async Task<IActionResult> Get(int OrderId)
        {
            Order item = _OrderRepo.Get(OrderId);
            if (item == null)
                return NotFound();

            return Ok(OrderDTO.ToDataTransferObject(item));
        }

        // POST api/<OrderController>
        [HttpPost("post")]
        public async Task<IActionResult> Post([FromBody] OrderDTO model)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var item = OrderDTO.FromDataTransferObject(model);
                    _OrderRepo.Insert(item);
                    // New id can be assigned here
                    return Ok(model);
                }
                else
                {
                    return BadRequest(model);
                }
            }
            catch
            {
            }
            return BadRequest();
        }

        // PUT api/<OrderController>/5
        [HttpPost("put")]
        public async Task<IActionResult> Put([FromBody] OrderDTO model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    var item = OrderDTO.FromDataTransferObject(model);
                    _OrderRepo.Update(item);
                    return Ok();
                }
                else
                {
                    return BadRequest(model);
                }
            }
            catch
            {
            }
            return BadRequest();
        }

        // DELETE api/<OrderController>/5
        [HttpPost("delete/{OrderId}")]
        public void Delete(int OrderId)
        {
            try
            {
                _OrderRepo.Delete(OrderId);
            }
            catch
            {
            }
        }
    }
}
