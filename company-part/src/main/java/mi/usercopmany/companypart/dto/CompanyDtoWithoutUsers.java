package mi.usercopmany.companypart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDtoWithoutUsers {
    private Long id;
    private String company_name;
    private Long budget;
}
