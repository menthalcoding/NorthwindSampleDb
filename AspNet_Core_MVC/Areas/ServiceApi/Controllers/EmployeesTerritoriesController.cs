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
    public class EmployeesTerritoriesController : ControllerBase
    {
        EmployeesTerritoriesRepository _EmployeesTerritoriesRepo;

        public EmployeesTerritoriesController()
        {
            _EmployeesTerritoriesRepo = new EmployeesTerritoriesRepository();
        }

        // GET: api/<EmployeesTerritoriesController>
        
        [HttpGet]        
        public async Task<IActionResult> Get()
        {
            List<EmployeesTerritories> items = _EmployeesTerritoriesRepo.List(0, 1000);
            if (items == null)
                return NotFound();

            return Ok(items.Select(i => EmployeesTerritoriesDTO.ToDataTransferObject(i)).ToList());
        }

        // GET api/<EmployeesTerritoriesController>/5
        [HttpGet("{EmpleymontId}/{TerritoryID}")]
        public async Task<IActionResult> Get(int EmpleymontId, string TerritoryID)
        {
            EmployeesTerritories item = _EmployeesTerritoriesRepo.Get(EmpleymontId, TerritoryID);
            if (item == null)
                return NotFound();

            return Ok(EmployeesTerritoriesDTO.ToDataTransferObject(item));
        }

        // POST api/<EmployeesTerritoriesController>
        [HttpPost("post")]
        public async Task<IActionResult> Post([FromBody] EmployeesTerritoriesDTO model)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var item = EmployeesTerritoriesDTO.FromDataTransferObject(model);
                    _EmployeesTerritoriesRepo.Insert(item);
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

        // PUT api/<EmployeesTerritoriesController>/5
        [HttpPost("put")]
        public async Task<IActionResult> Put([FromBody] EmployeesTerritoriesDTO model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    var item = EmployeesTerritoriesDTO.FromDataTransferObject(model);
                    _EmployeesTerritoriesRepo.Update(item);
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

        // DELETE api/<EmployeesTerritoriesController>/5
        [HttpPost("delete/{EmpleymontId}/{TerritoryID}")]
        public void Delete(int EmpleymontId, string TerritoryID)
        {
            try
            {
                _EmployeesTerritoriesRepo.Delete(EmpleymontId, TerritoryID);
            }
            catch
            {
            }
        }
    }
}
