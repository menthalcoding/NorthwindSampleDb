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
    public class SuppliersController : ControllerBase
    {
        SuppliersRepository _SuppliersRepo;

        public SuppliersController()
        {
            _SuppliersRepo = new SuppliersRepository();
        }

        // GET: api/<SuppliersController>
        
        [HttpGet]        
        public async Task<IActionResult> Get()
        {
            List<Suppliers> items = _SuppliersRepo.List(0, 1000);
            if (items == null)
                return NotFound();

            return Ok(items.Select(i => SuppliersDTO.ToDataTransferObject(i)).ToList());
        }

        // GET api/<SuppliersController>/5
        [HttpGet("{SupplierID}")]
        public async Task<IActionResult> Get(int SupplierID)
        {
            Suppliers item = _SuppliersRepo.Get(SupplierID);
            if (item == null)
                return NotFound();

            return Ok(SuppliersDTO.ToDataTransferObject(item));
        }

        // POST api/<SuppliersController>
        [HttpPost("post")]
        public async Task<IActionResult> Post([FromBody] SuppliersDTO model)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var item = SuppliersDTO.FromDataTransferObject(model);
                    _SuppliersRepo.Insert(item);
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

        // PUT api/<SuppliersController>/5
        [HttpPost("put")]
        public async Task<IActionResult> Put([FromBody] SuppliersDTO model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    var item = SuppliersDTO.FromDataTransferObject(model);
                    _SuppliersRepo.Update(item);
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

        // DELETE api/<SuppliersController>/5
        [HttpPost("delete/{SupplierID}")]
        public void Delete(int SupplierID)
        {
            try
            {
                _SuppliersRepo.Delete(SupplierID);
            }
            catch
            {
            }
        }
    }
}
