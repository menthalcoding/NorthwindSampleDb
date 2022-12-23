package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.EmployeesDto;
import HttpClient.Messages.Criteria.EmployeesCriteria;
import HttpClient.Messages.MessageBase.ResponseBase;

public class EmployeesResponse extends ResponseBase {
    /// <summary>
    /// Employees object.
    /// </summary>
    public EmployeesDto Employees;

    /// <summary>
    /// List of Employees.
    /// </summary>
    public List<EmployeesDto>
    EmployeesList;

    public int ItemCount;
    }
