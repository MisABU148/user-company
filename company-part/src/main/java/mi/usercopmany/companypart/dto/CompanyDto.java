package mi.usercopmany.companypart.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyDto {
    private Long id;
    private String company_name;
    private Long budget;
    private List<Long> employeeId;
}
