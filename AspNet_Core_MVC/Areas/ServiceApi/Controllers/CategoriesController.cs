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
    public class CategoriesController : ControllerBase
    {
        CategoriesRepository _CategoriesRepo;

        public CategoriesController()
        {
            _CategoriesRepo = new CategoriesRepository();
        }

        // GET: api/<CategoriesController>
        
        [HttpGet]        
        public async Task<IActionResult> Get()
        {
            List<Categories> items = _CategoriesRepo.List(0, 1000);
            if (items == null)
                return NotFound();

            return Ok(items.Select(i => CategoriesDTO.ToDataTransferObject(i)).ToList());
        }

        // GET api/<CategoriesController>/5
        [HttpGet("{CategoryID}")]
        public async Task<IActionResult> Get(int CategoryID)
        {
            Categories item = _CategoriesRepo.Get(CategoryID);
            if (item == null)
                return NotFound();

            return Ok(CategoriesDTO.ToDataTransferObject(item));
        }

        // POST api/<CategoriesController>
        [HttpPost("post")]
        public async Task<IActionResult> Post([FromBody] CategoriesDTO model)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var item = CategoriesDTO.FromDataTransferObject(model);
                    _CategoriesRepo.Insert(item);
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

        // PUT api/<CategoriesController>/5
        [HttpPost("put")]
        public async Task<IActionResult> Put([FromBody] CategoriesDTO model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    var item = CategoriesDTO.FromDataTransferObject(model);
                    _CategoriesRepo.Update(item);
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

        // DELETE api/<CategoriesController>/5
        [HttpPost("delete/{CategoryID}")]
        public void Delete(int CategoryID)
        {
            try
            {
                _CategoriesRepo.Delete(CategoryID);
            }
            catch
            {
            }
        }
    }
}
