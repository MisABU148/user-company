package mi.usercopmany.companypart.services;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import mi.usercopmany.companypart.dto.CompanyDto;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface CompanyService {
    void createCompany(@Valid CompanyDto companyDTO);
    List<CompanyDto> readCompany(@NotNull Long page, @NotNull Long pageSize);
    void deleteCompany(@NotNull @Positive Long id);
    void updateCompany(CompanyDto companyDto);
    void addUser(@NotNull @Positive Long userId, @NotNull @Positive Long companyId);
    void deleteUser(@NotNull @Positive Long Id);
    CompanyDto getCompany(@NotNull @Positive Long id);
    CompanyDto getOnlyCompany(@NotNull @Positive Long id);
}
