package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.SuppliersDto;
import HttpClient.Messages.Criteria.SuppliersCriteria;
import HttpClient.Messages.MessageBase.ResponseBase;

public class SuppliersResponse extends ResponseBase {
    /// <summary>
    /// Suppliers object.
    /// </summary>
    public SuppliersDto Suppliers;

    /// <summary>
    /// List of Suppliers.
    /// </summary>
    public List<SuppliersDto>
    SuppliersList;

    public int ItemCount;
    }
