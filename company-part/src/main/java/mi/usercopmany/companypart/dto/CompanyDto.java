package mi.usercopmany.companypart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CompanyDto {
    private Long id;
    @NotBlank
    private String company_name;
    @Min(0L)
    private Long budget;
    private List<UserInfoDto> users;
}
