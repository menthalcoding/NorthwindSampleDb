package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.OrderDetailsDto;
import HttpClient.Messages.Criteria.OrderDetailsCriteria;
import HttpClient.Messages.MessageBase.ResponseBase;

public class OrderDetailsResponse extends ResponseBase {
    /// <summary>
    /// OrderDetails object.
    /// </summary>
    public OrderDetailsDto OrderDetails;

    /// <summary>
    /// List of OrderDetails.
    /// </summary>
    public List<OrderDetailsDto>
    OrderDetailsList;

    public int ItemCount;
    }
