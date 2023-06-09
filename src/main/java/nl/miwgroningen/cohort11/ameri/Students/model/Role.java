package nl.miwgroningen.cohort11.ameri.Students.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Mohammed Alameri on 08/06/2023.
 * reflection of Roles type in database
 */
@Entity
@Setter
@Getter
public class Role {
    @Id
    private RoleType roleType;
}
