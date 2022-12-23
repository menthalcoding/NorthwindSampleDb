package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.CustomersCustomersDemoDto;
import HttpClient.Messages.Criteria.CustomersCustomersDemoCriteria;
import HttpClient.Messages.MessageBase.ResponseBase;

public class CustomersCustomersDemoResponse extends ResponseBase {
    /// <summary>
    /// CustomersCustomersDemo object.
    /// </summary>
    public CustomersCustomersDemoDto CustomersCustomersDemo;

    /// <summary>
    /// List of CustomersCustomersDemo.
    /// </summary>
    public List<CustomersCustomersDemoDto>
    CustomersCustomersDemoList;

    public int ItemCount;
    }
