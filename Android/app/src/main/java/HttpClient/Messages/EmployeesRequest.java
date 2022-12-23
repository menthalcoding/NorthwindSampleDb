package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.EmployeesDto;
import HttpClient.Messages.Criteria.EmployeesCriteria;
import HttpClient.Messages.MessageBase.RequestBase;

public class EmployeesRequest extends RequestBase {
    /// <summary>
    /// Selection criteria and sort order
    /// </summary>
    public EmployeesCriteria Criteria;

    /// <summary>
    /// Employees object.
    /// </summary>
    public EmployeesDto Employees;
}
