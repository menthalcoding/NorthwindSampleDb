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
    public class CustomersCustomersDemoController : ControllerBase
    {
        CustomersCustomersDemoRepository _CustomersCustomersDemoRepo;

        public CustomersCustomersDemoController()
        {
            _CustomersCustomersDemoRepo = new CustomersCustomersDemoRepository();
        }

        // GET: api/<CustomersCustomersDemoController>
        
        [HttpGet]        
        public async Task<IActionResult> Get()
        {
            List<CustomersCustomersDemo> items = _CustomersCustomersDemoRepo.List(0, 1000);
            if (items == null)
                return NotFound();

            return Ok(items.Select(i => CustomersCustomersDemoDTO.ToDataTransferObject(i)).ToList());
        }

        // GET api/<CustomersCustomersDemoController>/5
        [HttpGet("{CustomerID}/{CustomersTypeId}")]
        public async Task<IActionResult> Get(string CustomerID, string CustomersTypeId)
        {
            CustomersCustomersDemo item = _CustomersCustomersDemoRepo.Get(CustomerID, CustomersTypeId);
            if (item == null)
                return NotFound();

            return Ok(CustomersCustomersDemoDTO.ToDataTransferObject(item));
        }

        // POST api/<CustomersCustomersDemoController>
        [HttpPost("post")]
        public async Task<IActionResult> Post([FromBody] CustomersCustomersDemoDTO model)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var item = CustomersCustomersDemoDTO.FromDataTransferObject(model);
                    _CustomersCustomersDemoRepo.Insert(item);
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

        // PUT api/<CustomersCustomersDemoController>/5
        [HttpPost("put")]
        public async Task<IActionResult> Put([FromBody] CustomersCustomersDemoDTO model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    var item = CustomersCustomersDemoDTO.FromDataTransferObject(model);
                    _CustomersCustomersDemoRepo.Update(item);
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

        // DELETE api/<CustomersCustomersDemoController>/5
        [HttpPost("delete/{CustomerID}/{CustomersTypeId}")]
        public void Delete(string CustomerID, string CustomersTypeId)
        {
            try
            {
                _CustomersCustomersDemoRepo.Delete(CustomerID, CustomersTypeId);
            }
            catch
            {
            }
        }
    }
}
