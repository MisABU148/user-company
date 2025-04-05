package mi.usercopmany.companypart.controllers;

import mi.usercopmany.companypart.dto.CompanyDto;
import mi.usercopmany.companypart.services.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/create")
    private CompanyDto createCompany(@RequestBody CompanyDto companyDTO) {
        companyService.createCompany(companyDTO);
        log.info("company added");
        return companyDTO;
    }
    @GetMapping("/read")
    private List<CompanyDto> readCompany() {
        return companyService.readCompany();
    }

    @DeleteMapping("/delete/{id}")
    private void deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        log.info("company deleted");
    }
    @PutMapping("/update")
    public CompanyDto updateCompany(@RequestBody CompanyDto companyDto) {
        companyService.updateCompany(companyDto);
        log.info("company updated");
        return companyDto;
    }
}
