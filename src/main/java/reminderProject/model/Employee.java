package reminderProject.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpl;

    private String firstName;
    private String surname;

//    @Enumerated(value = EnumType.STRING)
    private TypeOfContract typeOfContract;
    private LocalDate finishContract;


    @OneToMany (mappedBy = "employee", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private List<ReminderEmployee> reminderEmployee;

    public Employee(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public Employee(String firstName, String surname, TypeOfContract typeOfContract, LocalDate finishContract) {
        this.firstName = firstName;
        this.surname = surname;
        this.typeOfContract = typeOfContract;
        this.finishContract = finishContract;
    }
//
//    public enum TypeOfContract {
//        UMOWA_O_PRACE_OKRES_PROBNY(1),
//        UMOWA_O_PRACE_NA_CZAS_OKRESLONY(2),
//        UMOWA_O_PRACE_NA_CZAS_NIEOKRESLONY(3);
//
//        TypeOfContract(int i) {
//        }
//    }
}

