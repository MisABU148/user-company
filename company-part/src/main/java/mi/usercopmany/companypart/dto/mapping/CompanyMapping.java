package mi.usercopmany.companypart.dto.mapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mi.usercopmany.companypart.dto.CompanyDto;
import mi.usercopmany.companypart.dto.UserInfoDto;
import mi.usercopmany.companypart.models.Company;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CompanyMapping {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    public CompanyDto mapToCompanyDto(Company entity){
        CompanyDto dto = new CompanyDto();
        dto.setId(entity.getId());
        dto.setCompany_name(entity.getCompany_name());
        dto.setBudget(entity.getBudget());
        List<UserInfoDto> users = new ArrayList<>();
        for (Long employeeId : entity.getEmployee()) {
            try {
                UserInfoDto userInfo = fetchUserInfo(employeeId);
                if (userInfo != null) {
                    users.add(userInfo);
                }
            } catch (Exception e) {
                log.error("Failed to fetch info for employee {}", employeeId, e);
            }
        }
        dto.setUsers(users);

        return dto;
    }

    private UserInfoDto fetchUserInfo(Long id) {
        String url = String.format("http://localhost:8085/readOnlyUser/%d", id);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Accept", "application/json")
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return parseUserResponse(response.body());
            } else {
                log.error("Failed to fetch user {}. Status: {}", id, response.statusCode());
                return null;
            }
        } catch (Exception e) {
            log.error("Error fetching user {}", id, e);
            return null;
        }
    }

    private UserInfoDto parseUserResponse(String jsonResponse) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            UserInfoDto dto = new UserInfoDto();

            dto.setId(rootNode.hasNonNull("id") ? rootNode.get("id").asLong() : null);
            dto.setFirstName(rootNode.hasNonNull("first_name") ? rootNode.get("first_name").asText() : null);
            dto.setLastName(rootNode.hasNonNull("last_name") ? rootNode.get("last_name").asText() : null);
            dto.setPhoneNumber(rootNode.hasNonNull("phone_number") ? rootNode.get("phone_number").asText() : null);

            return dto;
        } catch (JsonProcessingException e) {
            log.error("Failed to parse user response: {}", jsonResponse, e);
            throw new RuntimeException("Failed to parse user response", e);
        }
    }

    public Company mapToCompanyEntity(CompanyDto dto){
        Company entity = new Company();
        entity.setCompany_name(dto.getCompany_name());
        entity.setBudget(dto.getBudget());
        return entity;
    }
    public List<CompanyDto> mapToPagedCompanies(List<Company> companies) {
        return companies.stream()
                .map(company -> mapToCompanyDto(company))
                .toList();
    }

    public CompanyDto mapToCompanyDtoWithoutUsers(Company entity) {
        CompanyDto dto = new CompanyDto();
        dto.setId(entity.getId());
        dto.setCompany_name(entity.getCompany_name());
        dto.setBudget(entity.getBudget());
        return dto;
    }
}
