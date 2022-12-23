package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.ProductsDto;
import HttpClient.Messages.Criteria.ProductsCriteria;
import HttpClient.Messages.MessageBase.RequestBase;

public class ProductsRequest extends RequestBase {
    /// <summary>
    /// Selection criteria and sort order
    /// </summary>
    public ProductsCriteria Criteria;

    /// <summary>
    /// Products object.
    /// </summary>
    public ProductsDto Products;
}
