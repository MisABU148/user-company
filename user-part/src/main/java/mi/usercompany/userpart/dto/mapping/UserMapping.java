package mi.usercompany.userpart.dto.mapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mi.usercompany.userpart.dto.CompanyInfoDto;
import mi.usercompany.userpart.dto.UserDto;
import mi.usercompany.userpart.dto.UserDtoWithoutCompany;
import mi.usercompany.userpart.models.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserMapping {
    public UserDto mapToUserDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());

        HttpClient client = HttpClient.newHttpClient();
        String url = String.format("http://localhost:8084/readOnlyCompany/%d", entity.getCompanyId());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Accept", "application/json")
                .build();
        try {
            log.info("Request to company service");
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Response: " + response.body());
            if (response.statusCode() == 200) {
                CompanyInfoDto companyInfo = parseCompanyResponse(response.body());
                dto.setCompany(companyInfo);
                log.info("Received company info: {}", response.body());
            } else {
                log.error("Failed to get company info. Status code: {}", response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return dto;
    }
    private CompanyInfoDto parseCompanyResponse(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            CompanyInfoDto dto = new CompanyInfoDto();

            dto.setCompanyId(rootNode.hasNonNull("id") ? rootNode.get("id").asLong() : null);
            dto.setCompany_name(rootNode.hasNonNull("company_name") ? rootNode.get("company_name").asText() : null);
            dto.setBudget(rootNode.hasNonNull("budget") ? rootNode.get("budget").asLong() : null);

            return dto;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse company response", e);
        }
    }

    public User mapToUserEntity(UserDto dto) {
        User entity = new User();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setCompanyId(dto.getCompany().getCompanyId());
        return entity;
    }

    public List<UserDto> mapToPagedUser(List<User> users) {
        return users.stream()
                .map(user -> mapToUserDto(user))
                .toList();
    }

    public UserDtoWithoutCompany mapToUserDtoWithoutCompany(User entity) {
        UserDtoWithoutCompany user = new UserDtoWithoutCompany();
        user.setId(entity.getId());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        return user;
    }
}
