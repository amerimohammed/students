package nl.miwgroningen.cohort11.ameri.Students.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mohammed Alameri on 09/06/2023.
 * an employee of Students Training company
 */
@Entity
@Getter
@Setter
public class Employee extends User{

    private String job;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Cohort> cohorts = new HashSet<>();
}
