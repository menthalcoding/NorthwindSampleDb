package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.CategoriesDto;
import HttpClient.Messages.Criteria.CategoriesCriteria;
import HttpClient.Messages.MessageBase.ResponseBase;

public class CategoriesResponse extends ResponseBase {
    /// <summary>
    /// Categories object.
    /// </summary>
    public CategoriesDto Categories;

    /// <summary>
    /// List of Categories.
    /// </summary>
    public List<CategoriesDto>
    CategoriesList;

    public int ItemCount;
    }
