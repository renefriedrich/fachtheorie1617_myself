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
public class Vehicle extends AbstractPersistable<Long> {

    private String type;
    private String brand;
    private String model;
    private String numberPlate;
    private int seatNr;
    private double km;
    private double basicPrice;
    private int includedKm;
    private double pricePer100Km;
    private double penaltyPerDay;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Trip> trips;



}
