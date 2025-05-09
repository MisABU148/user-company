package mi.usercompany.userpart.repositories;

import mi.usercompany.userpart.dto.UserDto;
import mi.usercompany.userpart.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query(value = "UPDATE users SET first_name = :#{#userDto.firstName}, " +
            "last_name = :#{#userDto.lastName}, " +
            "phone_number = :#{#userDto.phoneNumber}, " +
            "company_id = :#{#userDto.companyId} " +
            "WHERE id = :#{#userDto.id}", nativeQuery = true)
    void updateById(@Param("userDto") UserDto userDto);


    User getUserById(Long id);
    @Query(value = "SELECT * FROM users ORDER BY id LIMIT :pageSize OFFSET :page;", nativeQuery = true)
    List<User> findPart(Long page, Long pageSize);
}
