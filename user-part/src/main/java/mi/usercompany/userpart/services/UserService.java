package mi.usercompany.userpart.services;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import mi.usercompany.userpart.dto.UserDto;
import mi.usercompany.userpart.dto.UserDtoWithoutCompany;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface UserService {
    void createUser(@Valid UserDto userDto);
    List<UserDto> getUsers(@NotNull @Min(0) Long page, @NotNull @Min(0) Long pageSize);
    void deleteUser(@NotNull @Positive Long id);
    void updateData(UserDto userDto);
    UserDto getUser(@NotNull @Positive Long id);
    UserDtoWithoutCompany getOnlyUser(@NotNull @Positive Long id);
}