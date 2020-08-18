package ReminderProject.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ReminderEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private TypeOfReminder typeOfReminder;
    private int amount;
    private LocalDate dateOfReminder;
    private PeriodOfReminder periodOfReminder;


    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employee employee;


}
