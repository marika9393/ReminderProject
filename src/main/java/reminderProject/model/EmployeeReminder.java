package reminderProject.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeReminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private EmployeeReminderType typeOfReminder;
    private int amount;
    private LocalDate dateOfReminder;

    @Enumerated(value = EnumType.STRING)
    private ReminderPeriod reminderPeriod;


    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employee employee;

    public EmployeeReminder(EmployeeReminderType typeOfReminder, int amount, LocalDate dateOfReminder, ReminderPeriod reminderPeriod) {
        this.typeOfReminder = typeOfReminder;
        this.amount = amount;
        this.dateOfReminder = dateOfReminder;
        this.reminderPeriod = reminderPeriod;
    }
}
