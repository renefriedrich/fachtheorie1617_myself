package fachtheorie.persistence;


import com.sun.org.apache.regexp.internal.RE;
import fachtheorie.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Query("select r from Employee e join e.reservations r, Vehicle v join v.reservations r" +
            " where v.numberPlate = :numberPlate and e.SVNR = :svnr and r.dateFrom <= :startDate")
    List<Reservation> findReservationByNumberPlateAndSVNR(@Param("numberPlate") String numberPlate,
                                                          @Param("svnr") String svnr,
                                                          @Param("startDate") LocalDate startDate);

    @Query("select r from Employee e join e.reservations r " +
            "where e.SVNR = :svnr and :date between r.dateFrom and r.dateTo")
    Reservation findReservationBySVNRAndDateFrom(@Param("svnr") String svnr,
                                                 @Param("date") LocalDate date);


    @Query("select r from Vehicle v join v.reservations r " +
            " where v.numberPlate = :numberPlate and r.dateFrom between :start and :end ")
    List<Reservation> findReservationByNumberPlateAndDateFromAndDateTo(@Param("numberPlate") String numberPlate,
                                                            @Param("end") LocalDate end,
                                                            @Param("start") LocalDate start);

}
