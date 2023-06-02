package nl.miwgroningen.cohort11.ameri.Students.repository;

import nl.miwgroningen.cohort11.ameri.Students.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
