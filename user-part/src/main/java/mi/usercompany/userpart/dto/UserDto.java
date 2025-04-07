package mi.usercompany.userpart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    Long id;
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @Pattern(regexp = "^\\+\\d{11}$")
    String phoneNumber;
    CompanyInfoDto company;
}
