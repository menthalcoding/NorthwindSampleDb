package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.CustomersDemographicsDto;
import HttpClient.Messages.Criteria.CustomersDemographicsCriteria;
import HttpClient.Messages.MessageBase.RequestBase;

public class CustomersDemographicsRequest extends RequestBase {
    /// <summary>
    /// Selection criteria and sort order
    /// </summary>
    public CustomersDemographicsCriteria Criteria;

    /// <summary>
    /// CustomersDemographics object.
    /// </summary>
    public CustomersDemographicsDto CustomersDemographics;
}
