package fachtheorie.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Employee extends AbstractPersistable<Long> {

    private String firstname;
    private String lastname;
    private String SVNR;

    @OneToMany
    private List<Reservation> reservations;

    @OneToMany
    private List<Trip> trips;

}
