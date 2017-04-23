package fachtheorie.service;

import fachtheorie.model.Reservation;
import fachtheorie.model.Trip;
import fachtheorie.persistence.ReservationRepository;
import fachtheorie.persistence.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional

public class TripService {


    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    TripRepository tripRepository;


    public void startTrip(String numberPlate, String SVNR){
        Reservation reservation = reservationRepository.findReservationByNumberPlateAndSVNR(numberPlate, SVNR, LocalDate.now()).get(0);
        Trip trip = new Trip();
        trip.setEmployee(reservation.getEmployee());
        trip.setVehicle(reservation.getVehicle());
        trip.setStartDate(LocalDate.now());
        trip.setKmBegin(reservation.getVehicle().getKm());

        try {
            tripRepository.save(trip);
        }
        catch (Exception e){
            e.getMessage();
        }

    }
}
