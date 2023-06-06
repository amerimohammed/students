package nl.miwgroningen.cohort11.ameri.Students.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Mohammed Alameri on 01/06/2023.
 * @project Training field or program that student will follow
 */
@Entity
@Setter
@Getter
public class Course {
    @Id@GeneratedValue
    private Long courseId;
    @Column(unique = true)
    private String name;
    private int period;

}
