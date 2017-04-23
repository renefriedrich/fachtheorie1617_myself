package fachtheorie.service;

import fachtheorie.model.Reservation;
import fachtheorie.persistence.ReservationRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
@Log4j
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public void extendReservation(String SVNR, String numberPlate, LocalDate newEnd) {
        Reservation reservation = reservationRepository.findReservationBySVNRAndDateFrom(SVNR, LocalDate.now());
        log.info(reservationRepository.findAll());
        if (reservationRepository.findReservationByNumberPlateAndDateFromAndDateTo(numberPlate, newEnd, reservation.getDateTo()).isEmpty()) {
            reservation.setDateTo(newEnd);
            try {
                reservationRepository.save(reservation);
            } catch (Exception e) {
                e.getMessage();
            }
        }

    }
}
