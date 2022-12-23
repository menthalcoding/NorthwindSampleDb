package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.OrderDto;
import HttpClient.Messages.Criteria.OrderCriteria;
import HttpClient.Messages.MessageBase.ResponseBase;

public class OrderResponse extends ResponseBase {
    /// <summary>
    /// Order object.
    /// </summary>
    public OrderDto Order;

    /// <summary>
    /// List of Order.
    /// </summary>
    public List<OrderDto>
    OrderList;

    public int ItemCount;
    }
