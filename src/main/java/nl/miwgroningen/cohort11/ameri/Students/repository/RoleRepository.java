package nl.miwgroningen.cohort11.ameri.Students.repository;

import nl.miwgroningen.cohort11.ameri.Students.model.Role;
import nl.miwgroningen.cohort11.ameri.Students.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, RoleType> {
}
