package com.xworkz.project.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="department")
public class DepartmentDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dep_id")
    private Integer id;

    @Column(name = "dep_type")
    private String departmentType;

    @Column(name = "dep_area")
    private String area;

    @Column(name = "dep_address")
    private String address;




    @Override
    public String toString() {
        return "DepartmentDto{" +
                "id=" + id +
                ", departmentType='" + departmentType + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
