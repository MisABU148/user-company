package mi.usercompany.userpart.services;

import mi.usercompany.userpart.dto.UserDto;
import mi.usercompany.userpart.dto.mapping.UserMapping;
import mi.usercompany.userpart.models.User;
import mi.usercompany.userpart.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapping userMapping;

    public void createUser(UserDto userDto) {
        User user = userMapping.mapToUserEntity(userDto);
        userRepository.save(user);
    }
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> userMapping.mapToUserDto(user))
                .toList();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateData(UserDto userDto) {
        userRepository.updateById(userDto);
    }
}
