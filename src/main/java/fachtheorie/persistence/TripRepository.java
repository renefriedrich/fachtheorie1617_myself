package fachtheorie.persistence;


import fachtheorie.model.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;


@Repository
@Transactional
public interface TripRepository extends CrudRepository<Trip, Long>{

}
