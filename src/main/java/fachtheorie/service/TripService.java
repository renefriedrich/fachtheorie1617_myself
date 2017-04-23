package fachtheorie.service;

import fachtheorie.model.Reservation;
import fachtheorie.model.Trip;
import fachtheorie.model.Vehicle;
import fachtheorie.persistence.ReservationRepository;
import fachtheorie.persistence.TripRepository;
import fachtheorie.persistence.VehicleRepository;
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

    @Autowired
    private VehicleRepository vehicleRepository;



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



    public double returnCar(String numberPlate){
        Trip trip = tripRepository.findOpenTrip(numberPlate);
        Vehicle vehicle = trip.getVehicle();
        double price = calculatePrice(vehicle.getBasicPrice(),vehicle.getPricePer100Km(),trip.getKmBegin(),vehicle.getKm());
        trip.setEndDate(LocalDate.now());
        trip.setKmEnd(vehicle.getKm());
        try {
            tripRepository.save(trip);
        }catch (Exception e){
            e.getMessage();
        }

        return price;
    }


    public double calculatePrice(double basicPrice, double pricePer100Km, double kmBegin, double km){
        double price = 0;
        price = basicPrice + (km - kmBegin) / 100 * pricePer100Km;
        return price;
    }

}
