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
    public class ShippersController : ControllerBase
    {
        ShippersRepository _ShippersRepo;

        public ShippersController()
        {
            _ShippersRepo = new ShippersRepository();
        }

        // GET: api/<ShippersController>
        
        [HttpGet]        
        public async Task<IActionResult> Get()
        {
            List<Shippers> items = _ShippersRepo.List(0, 1000);
            if (items == null)
                return NotFound();

            return Ok(items.Select(i => ShippersDTO.ToDataTransferObject(i)).ToList());
        }

        // GET api/<ShippersController>/5
        [HttpGet("{ShipperId}")]
        public async Task<IActionResult> Get(int ShipperId)
        {
            Shippers item = _ShippersRepo.Get(ShipperId);
            if (item == null)
                return NotFound();

            return Ok(ShippersDTO.ToDataTransferObject(item));
        }

        // POST api/<ShippersController>
        [HttpPost("post")]
        public async Task<IActionResult> Post([FromBody] ShippersDTO model)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var item = ShippersDTO.FromDataTransferObject(model);
                    _ShippersRepo.Insert(item);
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

        // PUT api/<ShippersController>/5
        [HttpPost("put")]
        public async Task<IActionResult> Put([FromBody] ShippersDTO model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    var item = ShippersDTO.FromDataTransferObject(model);
                    _ShippersRepo.Update(item);
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

        // DELETE api/<ShippersController>/5
        [HttpPost("delete/{ShipperId}")]
        public void Delete(int ShipperId)
        {
            try
            {
                _ShippersRepo.Delete(ShipperId);
            }
            catch
            {
            }
        }
    }
}
