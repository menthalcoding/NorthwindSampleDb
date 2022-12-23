package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.OrderDetailsDto;
import HttpClient.Messages.Criteria.OrderDetailsCriteria;
import HttpClient.Messages.MessageBase.RequestBase;

public class OrderDetailsRequest extends RequestBase {
    /// <summary>
    /// Selection criteria and sort order
    /// </summary>
    public OrderDetailsCriteria Criteria;

    /// <summary>
    /// OrderDetails object.
    /// </summary>
    public OrderDetailsDto OrderDetails;
}
