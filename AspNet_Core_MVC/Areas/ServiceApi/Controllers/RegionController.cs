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
    public class RegionController : ControllerBase
    {
        RegionRepository _RegionRepo;

        public RegionController()
        {
            _RegionRepo = new RegionRepository();
        }

        // GET: api/<RegionController>
        
        [HttpGet]        
        public async Task<IActionResult> Get()
        {
            List<Region> items = _RegionRepo.List(0, 1000);
            if (items == null)
                return NotFound();

            return Ok(items.Select(i => RegionDTO.ToDataTransferObject(i)).ToList());
        }

        // GET api/<RegionController>/5
        [HttpGet("{RegionID}")]
        public async Task<IActionResult> Get(int RegionID)
        {
            Region item = _RegionRepo.Get(RegionID);
            if (item == null)
                return NotFound();

            return Ok(RegionDTO.ToDataTransferObject(item));
        }

        // POST api/<RegionController>
        [HttpPost("post")]
        public async Task<IActionResult> Post([FromBody] RegionDTO model)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var item = RegionDTO.FromDataTransferObject(model);
                    _RegionRepo.Insert(item);
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

        // PUT api/<RegionController>/5
        [HttpPost("put")]
        public async Task<IActionResult> Put([FromBody] RegionDTO model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    var item = RegionDTO.FromDataTransferObject(model);
                    _RegionRepo.Update(item);
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

        // DELETE api/<RegionController>/5
        [HttpPost("delete/{RegionID}")]
        public void Delete(int RegionID)
        {
            try
            {
                _RegionRepo.Delete(RegionID);
            }
            catch
            {
            }
        }
    }
}
