package mi.usercompany.userpart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    Long id;
    String firstName;
    String lastName;
    String phoneNumber;
    Long companyId;
}
