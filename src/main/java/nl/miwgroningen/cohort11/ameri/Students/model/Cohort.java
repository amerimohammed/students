package nl.miwgroningen.cohort11.ameri.Students.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Mohammed Alameri on 31/05/2023.
 * @project reflection of cohort attributes in the database
 */
@Entity
@Setter
@Getter
public class Cohort {
    @Id
    @GeneratedValue
    private Long cohortId;

    private String Name;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @ManyToOne
    private Training training;
}
