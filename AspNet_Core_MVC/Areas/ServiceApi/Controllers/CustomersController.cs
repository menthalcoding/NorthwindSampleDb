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
    public class CustomersController : ControllerBase
    {
        CustomersRepository _CustomersRepo;

        public CustomersController()
        {
            _CustomersRepo = new CustomersRepository();
        }

        // GET: api/<CustomersController>
        
        [HttpGet]        
        public async Task<IActionResult> Get()
        {
            List<Customers> items = _CustomersRepo.List(0, 1000);
            if (items == null)
                return NotFound();

            return Ok(items.Select(i => CustomersDTO.ToDataTransferObject(i)).ToList());
        }

        // GET api/<CustomersController>/5
        [HttpGet("{CustomerID}")]
        public async Task<IActionResult> Get(string CustomerID)
        {
            Customers item = _CustomersRepo.Get(CustomerID);
            if (item == null)
                return NotFound();

            return Ok(CustomersDTO.ToDataTransferObject(item));
        }

        // POST api/<CustomersController>
        [HttpPost("post")]
        public async Task<IActionResult> Post([FromBody] CustomersDTO model)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var item = CustomersDTO.FromDataTransferObject(model);
                    _CustomersRepo.Insert(item);
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

        // PUT api/<CustomersController>/5
        [HttpPost("put")]
        public async Task<IActionResult> Put([FromBody] CustomersDTO model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    var item = CustomersDTO.FromDataTransferObject(model);
                    _CustomersRepo.Update(item);
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

        // DELETE api/<CustomersController>/5
        [HttpPost("delete/{CustomerID}")]
        public void Delete(string CustomerID)
        {
            try
            {
                _CustomersRepo.Delete(CustomerID);
            }
            catch
            {
            }
        }
    }
}
