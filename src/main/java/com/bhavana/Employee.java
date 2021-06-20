package com.bhavana;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import java.util.ArrayList;
import javax.persistence.FetchType;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy="employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Dependent.class)
    @JsonManagedReference
    private List<Dependent> dependents;
    @JsonManagedReference
    @OneToOne(mappedBy="employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Qualification.class)
    private Qualification qualification;

    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + id +
                ", First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'' +
                '}';
    }

    public List<Dependent> getDependents(){
        if (this.dependents == null) {
            this.dependents = new ArrayList<>();
        }
        return this.dependents;
    }

    public void addDependent(Dependent dependent){
        getDependents().add(dependent);
        dependent.setEmployee(this);
    }

    public void deleteDependent(Long dependentId){
        for(Dependent dependent: getDependents()){
            if (dependent.getId() == dependentId){
                this.dependents.remove(dependent);
                continue;
            }
        }   
    }

    public Qualification getQualification(){
        return this.qualification;
    }

    public void setQualification(Qualification qualification){
        qualification.setEmployee(this);
        this.qualification = qualification;
    }
}
