package nl.miwgroningen.cohort11.ameri.Students.repository;

import nl.miwgroningen.cohort11.ameri.Students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mohammed Alameri on 31/05/2023.
 * @project provides operations to interact with Student table on the database
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}
