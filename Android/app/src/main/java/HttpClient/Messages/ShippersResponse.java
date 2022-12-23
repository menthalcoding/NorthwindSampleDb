package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.ShippersDto;
import HttpClient.Messages.Criteria.ShippersCriteria;
import HttpClient.Messages.MessageBase.ResponseBase;

public class ShippersResponse extends ResponseBase {
    /// <summary>
    /// Shippers object.
    /// </summary>
    public ShippersDto Shippers;

    /// <summary>
    /// List of Shippers.
    /// </summary>
    public List<ShippersDto>
    ShippersList;

    public int ItemCount;
    }
