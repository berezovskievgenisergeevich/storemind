package api.model.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class CreateNewCustomerErrorResponseModel {
    @JsonProperty("error")
    private ErrorDetails error;
}
