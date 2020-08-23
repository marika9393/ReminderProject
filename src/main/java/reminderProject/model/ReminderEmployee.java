package reminderProject.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReminderEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private TypeOfReminder typeOfReminder;
    private int amount;
    private LocalDate dateOfReminder;

    @Enumerated(value = EnumType.STRING)
    private PeriodOfReminder periodOfReminder;


    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employee employee;

    public ReminderEmployee(TypeOfReminder typeOfReminder, int amount, LocalDate dateOfReminder, PeriodOfReminder periodOfReminder) {
        this.typeOfReminder = typeOfReminder;
        this.amount = amount;
        this.dateOfReminder = dateOfReminder;
        this.periodOfReminder = periodOfReminder;
    }
}
