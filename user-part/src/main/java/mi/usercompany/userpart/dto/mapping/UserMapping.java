package mi.usercompany.userpart.dto.mapping;

import mi.usercompany.userpart.dto.UserDto;
import mi.usercompany.userpart.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapping {
    public UserDto mapToUserDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setCompanyId(entity.getCompanyId());
        return dto;
    }
    public User mapToUserEntity(UserDto dto) {
        User entity = new User();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setCompanyId(dto.getCompanyId());
        return entity;
    }
}
