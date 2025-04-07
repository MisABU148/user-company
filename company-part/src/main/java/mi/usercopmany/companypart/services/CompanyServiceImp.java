package mi.usercopmany.companypart.services;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import mi.usercopmany.companypart.controllers.ErrorHandlingControllerAdvice;
import mi.usercopmany.companypart.dto.CompanyDto;
import mi.usercopmany.companypart.dto.mapping.CompanyMapping;
import mi.usercopmany.companypart.models.Company;
import mi.usercopmany.companypart.repositories.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CompanyServiceImp implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapping companyMapping;
    @Override
    public void createCompany(CompanyDto companyDTO) {
        Company newCompany = companyMapping.mapToCompanyEntity(companyDTO);
        companyRepository.save(newCompany);
    }
    @Override
    public List<CompanyDto> readCompany(Long page, Long pageSize) {
        List<Company> companies = companyRepository.findPart(page, pageSize);
        return companyMapping.mapToPagedCompanies(companies);
    }
    @Override
    public void deleteCompany(Long id) {
        companyRepository.findById(id)
                .ifPresentOrElse(
                        user -> companyRepository.deleteById(id),
                        () -> { throw new ErrorHandlingControllerAdvice.CompanyNotFoundException(id); }
                );
    }
    @Override
    public void updateCompany(CompanyDto companyDto) {
        companyRepository.findById(companyDto.getId())
                .ifPresentOrElse(
                        user -> companyRepository.updateById(companyDto),
                        () -> { throw new ErrorHandlingControllerAdvice.CompanyNotFoundException(companyDto.getId()); }
                );
    }
    @Override
    public void addUser( Long userId, Long companyId) {
        companyRepository.addUserById(userId, companyId);
    }
    @Override
    public void deleteUser(Long Id) {
        companyRepository.deleteUserById(Id);
    }
    @Override
    public CompanyDto getCompany(Long id) {
        CompanyDto companyDto = companyMapping.mapToCompanyDto(companyRepository.getCompanyById(id));
        return companyDto;
    }
    @Override
    public CompanyDto getOnlyCompany(Long id) {
        CompanyDto companyDto = companyMapping.mapToCompanyDtoWithoutUsers(companyRepository.getCompanyById(id));
        return companyDto;
    }
}
