package com.xworkz.project.dto;

import javax.persistence.*;

@Entity
@Table(name="department_admin")
public class DepartmentAdminDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dep_admin_id")
    private Integer id;

    @Column(name = "dep_admin_name")
    private String adminName;

    @Column(name = "dep_admin_department_name")
    private String departmentName;

    @Column(name = "dep_admin_email")
    private String email;

    //this is for login page
    // @Column(unique = true)
    private String password;

    @Column(name = "dep_admin_number")
    private Long contactNumber;

    @Column(name = "dep_admin_alternate_number")
    private Long alternateContactNumber;

    //email locked update in database
    @Column(name = "failed_attempt")
    private Integer failedAttempt=0;


    public static final int MAX_LOGIN_ATTEMPTS=3;
    @Column(name = "account_locked")
    private Boolean accountLocked=false;

    @ManyToOne
    @JoinColumn(name = "department_id",referencedColumnName = "dep_id")
    private DepartmentDto department;


    @Override
    public String toString() {
        return "DepartmentAdminDto{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contactNumber=" + contactNumber +
                ", alternateContactNumber=" + alternateContactNumber +
                ", failedAttempt=" + failedAttempt +
                ", accountLocked=" + accountLocked +
                ", department=" + department +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Long getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public void setAlternateContactNumber(Long alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getFailedAttempt() {
        return failedAttempt;
    }

    public void setFailedAttempt(Integer failedAttempt) {
        this.failedAttempt = failedAttempt;
    }

    public Boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    public Boolean getAccountLocked() {
        return accountLocked;
    }
}
