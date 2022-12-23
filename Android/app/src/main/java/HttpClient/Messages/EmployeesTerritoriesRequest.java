package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.EmployeesTerritoriesDto;
import HttpClient.Messages.Criteria.EmployeesTerritoriesCriteria;
import HttpClient.Messages.MessageBase.RequestBase;

public class EmployeesTerritoriesRequest extends RequestBase {
    /// <summary>
    /// Selection criteria and sort order
    /// </summary>
    public EmployeesTerritoriesCriteria Criteria;

    /// <summary>
    /// EmployeesTerritories object.
    /// </summary>
    public EmployeesTerritoriesDto EmployeesTerritories;
}
