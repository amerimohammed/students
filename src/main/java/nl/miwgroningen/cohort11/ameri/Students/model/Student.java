package nl.miwgroningen.cohort11.ameri.Students.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Mohammed Alameri on 31/05/2023.
 * attributes of the student
 */
@Entity
@Getter
@Setter
public class Student extends User {
    @ManyToOne
    private Cohort cohort;
}
