package mi.usercopmany.companypart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {
    @JsonProperty("id") Long id;
    @JsonProperty("first_name") String firstName;
    @JsonProperty("last_name") String lastName;
    @JsonProperty("phone_number") String phoneNumber;
    public UserInfoDto() {}
}
