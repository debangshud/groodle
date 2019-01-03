package org.dasgupta.sample.springboot.jpa.entity;

import javax.persistence.*;

//DEPARTMENT_ID	NUMBER(4,0)
//DEPARTMENT_NAME	VARCHAR2(30 BYTE)
//MANAGER_ID	NUMBER(6,0)
//LOCATION_ID	NUMBER(4,0)

@Entity
@Table(name = "DEPARTMENTS")
public class Department {

    @Id
    @Column(name = "DEPARTMENT_ID")
    private Integer id;

    @Column(name = "DEPARTMENT_NAME")
    private String name;

//    @OneToOne
//    @JoinColumn(name = "MANAGER_ID")
//    private Employee manager;

    private String locationId;

    public Department() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Employee getManager() {
//        return manager;
//    }
//
//    public void setManager(Employee manager) {
//        this.manager = manager;
//    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
