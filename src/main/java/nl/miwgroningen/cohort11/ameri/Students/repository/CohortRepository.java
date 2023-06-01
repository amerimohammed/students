package nl.miwgroningen.cohort11.ameri.Students.repository;

import nl.miwgroningen.cohort11.ameri.Students.model.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mohammed Alameri on 31/05/2023.
 * @project handles interactions with table Cohort on the database
 */
public interface CohortRepository extends JpaRepository<Cohort, Long> {
}
