package nl.miwgroningen.cohort11.ameri.Students.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Mohammed Alameri on 31/05/2023.
 * @project attributes of the student
 */
@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue
    private Long studentId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    @ManyToOne
    private Cohort cohort;
}
