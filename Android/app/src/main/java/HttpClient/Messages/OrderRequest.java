package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.OrderDto;
import HttpClient.Messages.Criteria.OrderCriteria;
import HttpClient.Messages.MessageBase.RequestBase;

public class OrderRequest extends RequestBase {
    /// <summary>
    /// Selection criteria and sort order
    /// </summary>
    public OrderCriteria Criteria;

    /// <summary>
    /// Order object.
    /// </summary>
    public OrderDto Order;
}
