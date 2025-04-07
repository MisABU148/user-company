package mi.usercompany.userpart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyInfoDto {
    @JsonProperty("id") Long companyId;
    @JsonProperty("company_name") String company_name;
    @JsonProperty("budget") Long budget;

    public CompanyInfoDto() {}

}
