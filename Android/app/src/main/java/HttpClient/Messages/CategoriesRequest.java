package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.CategoriesDto;
import HttpClient.Messages.Criteria.CategoriesCriteria;
import HttpClient.Messages.MessageBase.RequestBase;

public class CategoriesRequest extends RequestBase {
    /// <summary>
    /// Selection criteria and sort order
    /// </summary>
    public CategoriesCriteria Criteria;

    /// <summary>
    /// Categories object.
    /// </summary>
    public CategoriesDto Categories;
}
