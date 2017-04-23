package fachtheorie.persistence;


import fachtheorie.model.Trip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;



@Repository
@Transactional
public interface TripRepository extends CrudRepository<Trip, Long>{

    @Query("select t from Vehicle v join v.trips t" +
            " where v.numberPlate = :numberPlate and t.endDate is null")
    Trip findOpenTrip(@Param("numberPlate") String numberPlate);

}
