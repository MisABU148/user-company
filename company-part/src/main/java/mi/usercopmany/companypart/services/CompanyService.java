package mi.usercopmany.companypart.services;

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
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapping companyMapping;

    public void createCompany(CompanyDto companyDTO) {
        Company newCompany = companyMapping.mapToCompanyEntity(companyDTO);
        companyRepository.save(newCompany);
    }

    public List<CompanyDto> readCompany() {
        return companyRepository.findAll().stream()
                .map(company -> companyMapping.mapToCompanyDto(company))
                .toList();
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    public void updateCompany(CompanyDto companyDto) {
        companyRepository.updateById(companyDto);
    }
}
