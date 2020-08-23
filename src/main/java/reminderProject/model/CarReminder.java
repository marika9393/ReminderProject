package reminderProject.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class CarReminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRem;

    @Enumerated(value = EnumType.STRING)
    private ReminderType type;
    private int amount;

    private LocalDate date;
    @Enumerated(value = EnumType.STRING)
    private ReminderPeriod period;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Car car;

    public CarReminder(ReminderType type, int amount, LocalDate date, ReminderPeriod period) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.period = period;
    }


}
