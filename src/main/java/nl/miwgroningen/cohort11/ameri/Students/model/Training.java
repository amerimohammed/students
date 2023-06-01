package nl.miwgroningen.cohort11.ameri.Students.model;

import lombok.Getter;
import lombok.Setter;

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
public class Training {
    @Id@GeneratedValue
    private Long trainingId;
    private String name;

}
