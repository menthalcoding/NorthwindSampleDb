package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.ProductsDto;
import HttpClient.Messages.Criteria.ProductsCriteria;
import HttpClient.Messages.MessageBase.ResponseBase;

public class ProductsResponse extends ResponseBase {
    /// <summary>
    /// Products object.
    /// </summary>
    public ProductsDto Products;

    /// <summary>
    /// List of Products.
    /// </summary>
    public List<ProductsDto>
    ProductsList;

    public int ItemCount;
    }
