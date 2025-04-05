package mi.usercopmany.companypart.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "company")
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    @Column(name = "company_name")
    private String company_name;
    @Column(name = "budget")
    private Long budget;
    @ElementCollection
    @CollectionTable(name = "employee_id")
    private List<Long> employee;

    public Company() {
    }
}
