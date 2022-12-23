package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.SuppliersDto;
import HttpClient.Messages.Criteria.SuppliersCriteria;
import HttpClient.Messages.MessageBase.RequestBase;

public class SuppliersRequest extends RequestBase {
    /// <summary>
    /// Selection criteria and sort order
    /// </summary>
    public SuppliersCriteria Criteria;

    /// <summary>
    /// Suppliers object.
    /// </summary>
    public SuppliersDto Suppliers;
}
