package com.bhavana;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="qualifications")
public class Qualification {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "employeeId")
    private Employee employee;

    public Qualification() {
    }

    public Qualification(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Qualification(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Qualification{" +
                "ID=" + id +
                ", Type ='" + type + '\'' +
                ", Employee='" + employee.getId() + '\'' +
                '}';
    }

}
