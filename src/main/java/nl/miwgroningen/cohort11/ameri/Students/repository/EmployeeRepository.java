package nl.miwgroningen.cohort11.ameri.Students.repository;

import nl.miwgroningen.cohort11.ameri.Students.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
