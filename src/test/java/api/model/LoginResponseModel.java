package api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class LoginResponseModel {
    @JsonProperty("a_session_storemind-seid-dev_legacy")
    private String a_session_dev_legacy;

    @JsonProperty("a_session_storemind-seid-dev")
    private String a_session_dev;


}
