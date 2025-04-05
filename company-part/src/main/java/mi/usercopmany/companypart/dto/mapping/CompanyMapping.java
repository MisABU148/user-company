package mi.usercopmany.companypart.dto.mapping;

import mi.usercopmany.companypart.dto.CompanyDto;
import mi.usercopmany.companypart.models.Company;
import org.springframework.stereotype.Service;

@Service
public class CompanyMapping {
    public CompanyDto mapToCompanyDto(Company entity){
        CompanyDto dto = new CompanyDto();
        dto.setId(entity.getId());
        dto.setCompany_name(entity.getCompany_name());
        dto.setBudget(entity.getBudget());
        dto.setEmployeeId(entity.getEmployee());
        return dto;
    }
    public Company mapToCompanyEntity(CompanyDto dto){
        Company entity = new Company();
        entity.setCompany_name(dto.getCompany_name());
        entity.setBudget(dto.getBudget());
        return entity;
    }
}
