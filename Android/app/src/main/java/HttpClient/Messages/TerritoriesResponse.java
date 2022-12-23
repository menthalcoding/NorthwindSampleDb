package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.TerritoriesDto;
import HttpClient.Messages.Criteria.TerritoriesCriteria;
import HttpClient.Messages.MessageBase.ResponseBase;

public class TerritoriesResponse extends ResponseBase {
    /// <summary>
    /// Territories object.
    /// </summary>
    public TerritoriesDto Territories;

    /// <summary>
    /// List of Territories.
    /// </summary>
    public List<TerritoriesDto>
    TerritoriesList;

    public int ItemCount;
    }
