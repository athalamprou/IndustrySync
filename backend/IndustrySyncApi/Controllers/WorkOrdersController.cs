using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using IndustrySyncApi.Data;
using IndustrySyncApi.Models;

namespace IndustrySyncApi.Controllers;

[Route("api/[controller]")]
[ApiController]
public class WorkOrdersController : ControllerBase
{
    private readonly AppDbContext _context;

    // This connects the controller to your database bridge
    public WorkOrdersController(AppDbContext context)
    {
        _context = context;
    }

    // GET: api/WorkOrders (This will show all your tasks)
    [HttpGet]
    public async Task<ActionResult<IEnumerable<WorkOrder>>> GetWorkOrders()
    {
        return await _context.WorkOrders.ToListAsync();
    }

    // POST: api/WorkOrders (This will let you add a new task)
    [HttpPost]
    public async Task<ActionResult<WorkOrder>> CreateWorkOrder(WorkOrder workOrder)
    {
        _context.WorkOrders.Add(workOrder);
        await _context.SaveChangesAsync();

        return CreatedAtAction(nameof(GetWorkOrders), new { id = workOrder.Id }, workOrder);
    }

    [HttpPut("{id}")]
    public async Task<IActionResult> PutWorkOrder(long id, WorkOrder workOrder)
    {
        if (id != workOrder.Id)
        {
            return BadRequest();
        }

        _context.Entry(workOrder).State = EntityState.Modified;

        try
        {
            await _context.SaveChangesAsync();
        }
        catch (DbUpdateConcurrencyException)
        {
            if (!_context.WorkOrders.Any(e => e.Id == id))
            {
                return NotFound();
            }
            else
            {
                throw;
            }
        }

        return NoContent();
    }

}