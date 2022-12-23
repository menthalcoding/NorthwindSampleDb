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
    public class EmployeesController : ControllerBase
    {
        EmployeesRepository _EmployeesRepo;

        public EmployeesController()
        {
            _EmployeesRepo = new EmployeesRepository();
        }

        // GET: api/<EmployeesController>
        
        [HttpGet]        
        public async Task<IActionResult> Get()
        {
            List<Employees> items = _EmployeesRepo.List(0, 1000);
            if (items == null)
                return NotFound();

            return Ok(items.Select(i => EmployeesDTO.ToDataTransferObject(i)).ToList());
        }

        // GET api/<EmployeesController>/5
        [HttpGet("{EmployeeID}")]
        public async Task<IActionResult> Get(int EmployeeID)
        {
            Employees item = _EmployeesRepo.Get(EmployeeID);
            if (item == null)
                return NotFound();

            return Ok(EmployeesDTO.ToDataTransferObject(item));
        }

        // POST api/<EmployeesController>
        [HttpPost("post")]
        public async Task<IActionResult> Post([FromBody] EmployeesDTO model)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var item = EmployeesDTO.FromDataTransferObject(model);
                    _EmployeesRepo.Insert(item);
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

        // PUT api/<EmployeesController>/5
        [HttpPost("put")]
        public async Task<IActionResult> Put([FromBody] EmployeesDTO model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    var item = EmployeesDTO.FromDataTransferObject(model);
                    _EmployeesRepo.Update(item);
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

        // DELETE api/<EmployeesController>/5
        [HttpPost("delete/{EmployeeID}")]
        public void Delete(int EmployeeID)
        {
            try
            {
                _EmployeesRepo.Delete(EmployeeID);
            }
            catch
            {
            }
        }
    }
}
