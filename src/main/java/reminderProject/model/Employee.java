package reminderProject.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpl;

    private String firstname;
    private String surname;

    @Enumerated(value = EnumType.STRING)
    private EmployeeTypeOfContract typeOfContract;

    private LocalDate finishContract;

    @OneToMany (mappedBy = "employee", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private List<EmployeeReminder> reminderEmployee;

    public Employee(String firstName, String surname) {
        this.firstname = firstName;
        this.surname = surname;
    }

    public Employee(String firstName, String surname, EmployeeTypeOfContract typeOfContract, LocalDate finishContract) {
        this.firstname = firstName;
        this.surname = surname;
        this.typeOfContract = typeOfContract;
        this.finishContract = finishContract;
    }
}

