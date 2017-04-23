package fachtheorie.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Trip extends AbstractPersistable<Long> {

    private LocalDate startDate;
    private LocalDate endDate;
    private double kmBegin;
    private double kmEnd;

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private Employee employee;

}
