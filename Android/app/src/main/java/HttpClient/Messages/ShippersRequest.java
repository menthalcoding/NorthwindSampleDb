package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.ShippersDto;
import HttpClient.Messages.Criteria.ShippersCriteria;
import HttpClient.Messages.MessageBase.RequestBase;

public class ShippersRequest extends RequestBase {
    /// <summary>
    /// Selection criteria and sort order
    /// </summary>
    public ShippersCriteria Criteria;

    /// <summary>
    /// Shippers object.
    /// </summary>
    public ShippersDto Shippers;
}
