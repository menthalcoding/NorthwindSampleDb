package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.RegionDto;
import HttpClient.Messages.Criteria.RegionCriteria;
import HttpClient.Messages.MessageBase.ResponseBase;

public class RegionResponse extends ResponseBase {
    /// <summary>
    /// Region object.
    /// </summary>
    public RegionDto Region;

    /// <summary>
    /// List of Region.
    /// </summary>
    public List<RegionDto>
    RegionList;

    public int ItemCount;
    }
