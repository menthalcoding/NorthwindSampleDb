package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.CustomersDto;
import HttpClient.Messages.Criteria.CustomersCriteria;
import HttpClient.Messages.MessageBase.RequestBase;

public class CustomersRequest extends RequestBase {
    /// <summary>
    /// Selection criteria and sort order
    /// </summary>
    public CustomersCriteria Criteria;

    /// <summary>
    /// Customers object.
    /// </summary>
    public CustomersDto Customers;
}
