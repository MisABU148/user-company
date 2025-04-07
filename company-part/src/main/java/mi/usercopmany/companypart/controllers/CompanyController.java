package mi.usercopmany.companypart.controllers;

import mi.usercopmany.companypart.dto.CompanyDto;
import mi.usercopmany.companypart.services.CompanyService;
import mi.usercopmany.companypart.services.CompanyServiceImp;
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
    @GetMapping("/read/{page}/{size}")
    private List<CompanyDto> readCompany(@PathVariable("page")Long page, @PathVariable("size") Long pageSize) {
        return companyService.readCompany(page, pageSize);
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
    @PostMapping("/addUser/{userId}/{companyId}")
    private void addUser(@PathVariable("userId") Long userId, @PathVariable("companyId") Long companyId) {
        companyService.addUser(userId, companyId);
        log.info("user added to company");
    }
    @DeleteMapping("/deleteUser/{id}")
    private void deleteUser(@PathVariable("id") Long id) {
        companyService.deleteUser(id);
        log.info("user"+ id +" deleted from company");
    }
    @GetMapping("/read/{id}")
    public CompanyDto getCompany(@PathVariable Long id) {
        return companyService.getCompany(id);
    }
    @GetMapping("/readOnlyCompany/{id}")
    public CompanyDto getOnlyCompany(@PathVariable Long id) {
        return companyService.getOnlyCompany(id);
    }
}
