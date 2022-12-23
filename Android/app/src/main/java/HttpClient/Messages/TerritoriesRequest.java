package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.TerritoriesDto;
import HttpClient.Messages.Criteria.TerritoriesCriteria;
import HttpClient.Messages.MessageBase.RequestBase;

public class TerritoriesRequest extends RequestBase {
    /// <summary>
    /// Selection criteria and sort order
    /// </summary>
    public TerritoriesCriteria Criteria;

    /// <summary>
    /// Territories object.
    /// </summary>
    public TerritoriesDto Territories;
}
