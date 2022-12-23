package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.RegionDto;
import HttpClient.Messages.Criteria.RegionCriteria;
import HttpClient.Messages.MessageBase.RequestBase;

public class RegionRequest extends RequestBase {
    /// <summary>
    /// Selection criteria and sort order
    /// </summary>
    public RegionCriteria Criteria;

    /// <summary>
    /// Region object.
    /// </summary>
    public RegionDto Region;
}
