package ReminderProject.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpl;

    private String firstName;
    private String surname;
    private TypeOfContract typeOfContract;
    private LocalDate finishContract;


    @OneToMany (mappedBy = "employee", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private List<ReminderEployee> reminderEmployee;

    public Employee(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }
}
