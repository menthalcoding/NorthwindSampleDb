package HttpClient.Messages;

import java.util.List;
import HttpClient.DataTransferObjects.CustomersDemographicsDto;
import HttpClient.Messages.Criteria.CustomersDemographicsCriteria;
import HttpClient.Messages.MessageBase.ResponseBase;

public class CustomersDemographicsResponse extends ResponseBase {
    /// <summary>
    /// CustomersDemographics object.
    /// </summary>
    public CustomersDemographicsDto CustomersDemographics;

    /// <summary>
    /// List of CustomersDemographics.
    /// </summary>
    public List<CustomersDemographicsDto>
    CustomersDemographicsList;

    public int ItemCount;
    }
