package mi.usercopmany.companypart.repositories;

import mi.usercopmany.companypart.dto.CompanyDto;
import mi.usercopmany.companypart.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Modifying
    @Query(value = "UPDATE company SET company_name = :#{#companyDto.company_name}, " +
            "budget = :#{#companyDto.budget} " +
            "WHERE id = :#{#companyDto.id}", nativeQuery = true)
    void updateById(@Param("companyDto") CompanyDto companyDto);
    @Modifying
    @Query(value = "INSERT INTO employee_id VALUES (:companyId, :userId)", nativeQuery = true)
    void addUserById(@Param("userId") Long userId, @Param("companyId") Long companyId);
    @Modifying
    @Query(value = "DELETE FROM employee_id WHERE employee = :id", nativeQuery = true)
    void deleteUserById(Long id);
    Company getCompanyById(Long id);
    @Query(value = "SELECT * FROM company ORDER BY id LIMIT :pageSize OFFSET :page", nativeQuery = true)
    List<Company> findPart(@Param("page") Long page, @Param("pageSize") Long pageSize);
}
