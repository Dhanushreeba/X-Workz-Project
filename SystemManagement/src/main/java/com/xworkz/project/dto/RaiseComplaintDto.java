package com.xworkz.project.dto;

import javax.persistence.*;

@Entity
@Table(name = "complaint_raise")
public class RaiseComplaintDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id")
    private Integer complaintId;

    @Column(name = "complaint_type")
    private String complaintType;

    private String country;

    private String state;

    private String city;

    private String area;

    private String address;

    private String discription;

    private String status;

    //foriegnKey
//    @Column(name="signup_id")


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "signup_id", referencedColumnName = "id")
    private SignUpDto dto;



@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "dep_id",referencedColumnName = "dep_id")
    private DepartmentDto departmentDto;



    @Override
    public String toString() {
        return "RaiseComplaintDto{" +
                "complaintId=" + complaintId +
                ", complaintType='" + complaintType + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", discription='" + discription + '\'' +
                ", status='" + status + '\'' +
                ", dto=" + dto +
                '}';
    }

    public Integer getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SignUpDto getDto() {
        return dto;
    }

    public void setDto(SignUpDto dto) {
        this.dto = dto;

    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }
}
