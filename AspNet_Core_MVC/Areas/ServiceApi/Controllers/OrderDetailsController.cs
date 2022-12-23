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
    public class OrderDetailsController : ControllerBase
    {
        OrderDetailsRepository _OrderDetailsRepo;

        public OrderDetailsController()
        {
            _OrderDetailsRepo = new OrderDetailsRepository();
        }

        // GET: api/<OrderDetailsController>
        
        [HttpGet]        
        public async Task<IActionResult> Get()
        {
            List<OrderDetails> items = _OrderDetailsRepo.List(0, 1000);
            if (items == null)
                return NotFound();

            return Ok(items.Select(i => OrderDetailsDTO.ToDataTransferObject(i)).ToList());
        }

        // GET api/<OrderDetailsController>/5
        [HttpGet("{OrderId}/{ProductId}")]
        public async Task<IActionResult> Get(int OrderId, int ProductId)
        {
            OrderDetails item = _OrderDetailsRepo.Get(OrderId, ProductId);
            if (item == null)
                return NotFound();

            return Ok(OrderDetailsDTO.ToDataTransferObject(item));
        }

        // POST api/<OrderDetailsController>
        [HttpPost("post")]
        public async Task<IActionResult> Post([FromBody] OrderDetailsDTO model)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var item = OrderDetailsDTO.FromDataTransferObject(model);
                    _OrderDetailsRepo.Insert(item);
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

        // PUT api/<OrderDetailsController>/5
        [HttpPost("put")]
        public async Task<IActionResult> Put([FromBody] OrderDetailsDTO model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    var item = OrderDetailsDTO.FromDataTransferObject(model);
                    _OrderDetailsRepo.Update(item);
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

        // DELETE api/<OrderDetailsController>/5
        [HttpPost("delete/{OrderId}/{ProductId}")]
        public void Delete(int OrderId, int ProductId)
        {
            try
            {
                _OrderDetailsRepo.Delete(OrderId, ProductId);
            }
            catch
            {
            }
        }
    }
}
