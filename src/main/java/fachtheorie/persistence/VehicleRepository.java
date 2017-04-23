package fachtheorie.persistence;


import fachtheorie.model.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

}
