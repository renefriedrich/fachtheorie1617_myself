package fachtheorie.persistence;

import fachtheorie.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;


@Repository
@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Long>{


}
