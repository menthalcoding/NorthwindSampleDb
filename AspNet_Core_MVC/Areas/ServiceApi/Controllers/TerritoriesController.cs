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
    public class TerritoriesController : ControllerBase
    {
        TerritoriesRepository _TerritoriesRepo;

        public TerritoriesController()
        {
            _TerritoriesRepo = new TerritoriesRepository();
        }

        // GET: api/<TerritoriesController>
        
        [HttpGet]        
        public async Task<IActionResult> Get()
        {
            List<Territories> items = _TerritoriesRepo.List(0, 1000);
            if (items == null)
                return NotFound();

            return Ok(items.Select(i => TerritoriesDTO.ToDataTransferObject(i)).ToList());
        }

        // GET api/<TerritoriesController>/5
        [HttpGet("{TerritoryID}")]
        public async Task<IActionResult> Get(string TerritoryID)
        {
            Territories item = _TerritoriesRepo.Get(TerritoryID);
            if (item == null)
                return NotFound();

            return Ok(TerritoriesDTO.ToDataTransferObject(item));
        }

        // POST api/<TerritoriesController>
        [HttpPost("post")]
        public async Task<IActionResult> Post([FromBody] TerritoriesDTO model)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var item = TerritoriesDTO.FromDataTransferObject(model);
                    _TerritoriesRepo.Insert(item);
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

        // PUT api/<TerritoriesController>/5
        [HttpPost("put")]
        public async Task<IActionResult> Put([FromBody] TerritoriesDTO model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    var item = TerritoriesDTO.FromDataTransferObject(model);
                    _TerritoriesRepo.Update(item);
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

        // DELETE api/<TerritoriesController>/5
        [HttpPost("delete/{TerritoryID}")]
        public void Delete(string TerritoryID)
        {
            try
            {
                _TerritoriesRepo.Delete(TerritoryID);
            }
            catch
            {
            }
        }
    }
}
