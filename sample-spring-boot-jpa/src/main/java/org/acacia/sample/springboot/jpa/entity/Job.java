package org.dasgupta.sample.springboot.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//JOB_ID	VARCHAR2(10 BYTE)
//JOB_TITLE	VARCHAR2(35 BYTE)
//MIN_SALARY	NUMBER(6,0)
//MAX_SALARY	NUMBER(6,0)

@Entity
@Table(name = "JOBS")
public class Job {

    @Id
    @Column(name = "JOB_ID")
    private String id;

    @Column(name = "JOB_TITLE")
    private String title;
    private Integer minSalary;
    private Integer maxSalary;

    public Job() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }
}
