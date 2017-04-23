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

    private Employee employee = new Employee();

    private Reservation reservation = new Reservation();

    private Trip trip = new Trip();



    @Before
    public void setUp() throws Exception {
        vehicle.setNumberPlate("1234");
        vehicle.setKm(1000);
        employee.setSVNR("fri15403");
        employee.setFirstname("Rene");
        employee.setLastname("Friedrich");

        reservation.setEmployee(employee);
        reservation.setVehicle(vehicle);
        reservation.setDateFrom(LocalDate.now().minusDays(4));

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);

        employee.setReservations(reservations);
        vehicle.setReservations(reservations);


        reservationRepository.save(reservation);
        vehicleRepository.save(vehicle);
        employeeRepository.save(employee);
    }

    @After
    public void tearDown() throws Exception {
        reservationRepository.deleteAll();
        vehicleRepository.deleteAll();
        employeeRepository.deleteAll();

    }

    @Test
    public void startTrip() throws Exception {
        tripService.startTrip("1234","fri15403");
        List<Trip> trips = (List<Trip>) tripRepository.findAll();
        assertEquals(1,trips.size());
    }

}