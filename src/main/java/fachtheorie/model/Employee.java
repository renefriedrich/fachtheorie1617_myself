package fachtheorie.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
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

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Trip> trips;

}
