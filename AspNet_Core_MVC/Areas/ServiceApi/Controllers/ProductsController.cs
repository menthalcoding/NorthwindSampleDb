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
    public class ProductsController : ControllerBase
    {
        ProductsRepository _ProductsRepo;

        public ProductsController()
        {
            _ProductsRepo = new ProductsRepository();
        }

        // GET: api/<ProductsController>
        
        [HttpGet]        
        public async Task<IActionResult> Get()
        {
            List<Products> items = _ProductsRepo.List(0, 1000);
            if (items == null)
                return NotFound();

            return Ok(items.Select(i => ProductsDTO.ToDataTransferObject(i)).ToList());
        }

        // GET api/<ProductsController>/5
        [HttpGet("{ProductId}")]
        public async Task<IActionResult> Get(int ProductId)
        {
            Products item = _ProductsRepo.Get(ProductId);
            if (item == null)
                return NotFound();

            return Ok(ProductsDTO.ToDataTransferObject(item));
        }

        // POST api/<ProductsController>
        [HttpPost("post")]
        public async Task<IActionResult> Post([FromBody] ProductsDTO model)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var item = ProductsDTO.FromDataTransferObject(model);
                    _ProductsRepo.Insert(item);
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

        // PUT api/<ProductsController>/5
        [HttpPost("put")]
        public async Task<IActionResult> Put([FromBody] ProductsDTO model)
        {
            try
            {
                if (ModelState.IsValid) 
                {
                    var item = ProductsDTO.FromDataTransferObject(model);
                    _ProductsRepo.Update(item);
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

        // DELETE api/<ProductsController>/5
        [HttpPost("delete/{ProductId}")]
        public void Delete(int ProductId)
        {
            try
            {
                _ProductsRepo.Delete(ProductId);
            }
            catch
            {
            }
        }
    }
}
