package fachtheorie.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@Data
public class Reservation extends AbstractPersistable<Long> {

    private LocalDate dateFrom;
    private LocalDate dateTo;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Vehicle vehicle;
}
