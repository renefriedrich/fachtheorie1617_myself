package fachtheorie.service;

import fachtheorie.model.Employee;
import fachtheorie.model.Reservation;
import fachtheorie.model.Vehicle;
import fachtheorie.persistence.EmployeeRepository;
import fachtheorie.persistence.ReservationRepository;
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
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Vehicle vehicle = new Vehicle();

    private Employee employee = new Employee();

    private Reservation reservation = new Reservation();



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
        reservation.setDateTo(LocalDate.now().plusDays(5));

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);

        employee.setReservations(reservations);
        vehicle.setReservations(reservations);


        reservationRepository.save(reservation);
        employeeRepository.save(employee);
        vehicleRepository.save(vehicle);

    }

    @After
    public void tearDown() throws Exception {
        reservationRepository.deleteAll();
        vehicleRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    public void extendReservation() throws Exception {
        reservationService.extendReservation("fri15403","1234",LocalDate.now().plusDays(15));
        Reservation reservation = StreamSupport.stream(reservationRepository.findAll().spliterator(),false).collect(Collectors.toList()).get(0);
        assertEquals(LocalDate.now().plusDays(15),reservation.getDateTo());
    }

}