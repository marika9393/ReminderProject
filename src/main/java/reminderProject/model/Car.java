package reminderProject.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mark;
    private String model;

    private String regNum;

    @OneToMany(mappedBy = "car",fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private List<CarReminder> carReminder;

    public Car(String mark, String model, String regNum) {
        this.mark = mark;
        this.model = model;
        this.regNum = regNum;

    }
}
