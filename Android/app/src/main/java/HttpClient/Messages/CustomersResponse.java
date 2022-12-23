package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.CustomersDto;
import HttpClient.Messages.Criteria.CustomersCriteria;
import HttpClient.Messages.MessageBase.ResponseBase;

public class CustomersResponse extends ResponseBase {
    /// <summary>
    /// Customers object.
    /// </summary>
    public CustomersDto Customers;

    /// <summary>
    /// List of Customers.
    /// </summary>
    public List<CustomersDto>
    CustomersList;

    public int ItemCount;
    }
