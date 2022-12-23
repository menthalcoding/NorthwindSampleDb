package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.EmployeesTerritoriesDto;
import HttpClient.Messages.Criteria.EmployeesTerritoriesCriteria;
import HttpClient.Messages.MessageBase.ResponseBase;

public class EmployeesTerritoriesResponse extends ResponseBase {
    /// <summary>
    /// EmployeesTerritories object.
    /// </summary>
    public EmployeesTerritoriesDto EmployeesTerritories;

    /// <summary>
    /// List of EmployeesTerritories.
    /// </summary>
    public List<EmployeesTerritoriesDto>
    EmployeesTerritoriesList;

    public int ItemCount;
    }
