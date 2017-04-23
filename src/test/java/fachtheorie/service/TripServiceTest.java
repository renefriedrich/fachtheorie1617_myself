package fachtheorie.service;

import com.sun.org.apache.regexp.internal.RE;
import fachtheorie.model.Employee;
import fachtheorie.model.Reservation;
import fachtheorie.model.Trip;
import fachtheorie.model.Vehicle;
import fachtheorie.persistence.EmployeeRepository;
import fachtheorie.persistence.ReservationRepository;
import fachtheorie.persistence.TripRepository;
import fachtheorie.persistence.VehicleRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class TripServiceTest {

    @Autowired
    private TripService tripService;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Vehicle vehicle = new Vehicle();

    private Vehicle vehicle2 = new Vehicle();

    private Employee employee = new Employee();

    private Reservation reservation = new Reservation();

    private Trip trip = new Trip();



    @Before
    public void setUp() throws Exception {
        vehicle.setNumberPlate("1234");
        vehicle.setKm(8000);

        vehicle2.setNumberPlate("12345");
        vehicle2.setKm(10000);
        vehicle2.setBasicPrice(50);
        vehicle2.setPricePer100Km(70);

        employee.setSVNR("fri15403");
        employee.setFirstname("Rene");
        employee.setLastname("Friedrich");

        reservation.setEmployee(employee);
        reservation.setVehicle(vehicle);
        reservation.setDateFrom(LocalDate.now().minusDays(4));

        trip.setVehicle(vehicle2);
        trip.setEmployee(employee);
        trip.setStartDate(LocalDate.now().minusDays(20));
        trip.setKmBegin(4000);

        tripRepository.save(trip);
        reservationRepository.save(reservation);
        vehicleRepository.save(vehicle);
        employeeRepository.save(employee);
    }

    @After
    public void tearDown() throws Exception {
        reservationRepository.deleteAll();
        tripRepository.deleteAll();
        vehicleRepository.deleteAll();
        employeeRepository.deleteAll();

    }

    @Test
    public void startTrip() throws Exception {
        tripService.startTrip("1234","fri15403");
        List<Trip> trips = (List<Trip>) tripRepository.findAll();
        assertEquals(1,trips.size());
    }


    @Test
    public void returnCar() throws Exception {
        tripService.returnCar("12345");
        assertEquals(10000, trip.getKmEnd(),0.0001);
        assertEquals(LocalDate.now(), trip.getEndDate());
        assertEquals(4250.0, tripService.calculatePrice(50,70,4000,10000),0.0001);
    }

    @Test
    public void calculatePrice() throws Exception {
        assertEquals(4250.0,tripService.calculatePrice(50,70,4000,10000),0.0001);
    }

}