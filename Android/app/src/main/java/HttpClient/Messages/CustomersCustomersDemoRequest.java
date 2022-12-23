package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.CustomersCustomersDemoDto;
import HttpClient.Messages.Criteria.CustomersCustomersDemoCriteria;
import HttpClient.Messages.MessageBase.RequestBase;

public class CustomersCustomersDemoRequest extends RequestBase {
    /// <summary>
    /// Selection criteria and sort order
    /// </summary>
    public CustomersCustomersDemoCriteria Criteria;

    /// <summary>
    /// CustomersCustomersDemo object.
    /// </summary>
    public CustomersCustomersDemoDto CustomersCustomersDemo;
}
