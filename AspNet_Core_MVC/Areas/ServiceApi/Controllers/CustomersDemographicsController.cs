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
    public class CustomersDemographicsController : ControllerBase
    {
        CustomersDemographicsRepository _CustomersDemographicsRepo;

        public CustomersDemographicsController()
        {
            _CustomersDemographicsRepo = new CustomersDemographicsRepository();
        }

        // GET: api/<CustomersDemographicsController>
        
        [HttpGet]        
        public async Task<IActionResult> Get()
        {
            List<CustomersDemographics> items = _CustomersDemographicsRepo.List(0, 1000);
            if (items == null)
                return NotFound();

            return Ok(items.Select(i => CustomersDemographicsDTO.ToDataTransferObject(i)).ToList());
        }

        // GET api/<CustomersDemographicsController>/5
        [HttpGet("{CustomersTypeId}")]
        public async Task<IActionResult> Get(string CustomersTypeId)
        {
            CustomersDemographics item = _CustomersDemographicsRepo.Get(CustomersTypeId);
            if (item == null)
                return NotFound();

            return Ok(CustomersDemographicsDTO.ToDataTransferObject(item));
        }

        // POST api/<CustomersDemographicsController>
        [HttpPost("post")]
        public async Task<IActionResult> Post([FromBody] CustomersDemographicsDTO model)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var item = CustomersDemographicsDTO.FromDataTransferObject(model);
                    _CustomersDemographicsRepo.Insert(item);
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

        // PUT api/<CustomersDemographicsController>/5
        [HttpPost("put")]
        public async Task<IActionResult> Put([FromBody] CustomersDemographicsDTO model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    var item = CustomersDemographicsDTO.FromDataTransferObject(model);
                    _CustomersDemographicsRepo.Update(item);
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

        // DELETE api/<CustomersDemographicsController>/5
        [HttpPost("delete/{CustomersTypeId}")]
        public void Delete(string CustomersTypeId)
        {
            try
            {
                _CustomersDemographicsRepo.Delete(CustomersTypeId);
            }
            catch
            {
            }
        }
    }
}
