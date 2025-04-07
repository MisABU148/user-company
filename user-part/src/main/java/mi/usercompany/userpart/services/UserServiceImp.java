package mi.usercompany.userpart.services;

import mi.usercompany.userpart.controllers.ErrorHandlingControllerAdvice;
import mi.usercompany.userpart.dto.UserDto;
import mi.usercompany.userpart.dto.UserDtoWithoutCompany;
import mi.usercompany.userpart.dto.mapping.UserMapping;
import mi.usercompany.userpart.models.User;
import mi.usercompany.userpart.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapping userMapping;
    @Override
    public void createUser(UserDto userDto) {
        User user = userMapping.mapToUserEntity(userDto);
        userRepository.save(user);

        HttpClient client = HttpClient.newHttpClient();

        String url = String.format("http://localhost:8084/addUser/%d/%d", user.getId(),
                user.getCompanyId());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            log.info("Request to company service");
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Response: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<UserDto> getUsers(Long page, Long pageSize) {
        List<User> users = userRepository.findPart(page, pageSize);
        return userMapping.mapToPagedUser(users);
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .ifPresentOrElse(
                        user -> userRepository.delete(user),
                        () -> { throw new ErrorHandlingControllerAdvice.UserNotFoundException(id); }
                );

        HttpClient client = HttpClient.newHttpClient();

        String url = String.format("http://localhost:8084/deleteUser/%d", id);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        try {
            log.info("Request to company service");
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Response: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateData(UserDto userDto) {
        userRepository.findById(userDto.getId())
                .ifPresentOrElse(
                        user -> userRepository.updateById(userDto),
                        () -> { throw new ErrorHandlingControllerAdvice.UserNotFoundException(userDto.getId()); }
                );
    }
    @Override
    public UserDto getUser(Long id) {
        UserDto userDto = userMapping.mapToUserDto(userRepository.getUserById(id));
        return userDto;
    }
    @Override
    public UserDtoWithoutCompany getOnlyUser(Long id) {
        UserDtoWithoutCompany user = userMapping.mapToUserDtoWithoutCompany(
                userRepository.getUserById(id)
        );
        return user;
    }
}
