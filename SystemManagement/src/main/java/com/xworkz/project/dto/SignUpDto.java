package com.xworkz.project.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

//@Entity
//@Table
@Entity
@Table(name = "system_managment")
public class SignUpDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "firstName cannot be Null")
    @Size(min=3,max=30,message = "firstName should be min 3 max 30")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "firstName can only contain letters and spaces")
    private String firstName;

    @NotNull(message = "lastName cannot be Null")
    @Size(min=1,max=20,message = "lastName should be min 1 max 20")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "lastName can only contain letters and spaces")
    private String lastName;

    @Size(min=5,max=30,message = "email should be min 5 and  max 30")
    private String email;

    @NotNull(message = "contactNumber cannot be Null")
    @Max(9999999999L)
    private Long contactNumber;

    @NotNull(message = "alternateNumber cannot be Null")
    @Max(9999999999L)
    private Long alternateNumber;

    @Size(min=5,max=300,message = "address should be min 5 max 300")
    private String address;

    //this is for login page
   // @Column(unique = true)
    private String password;

    private String createdBy;

    private LocalDateTime createdOn;

    private String updatedBy;

    private LocalDateTime updatedOn;

    private boolean isActive;

    //to lock account after 3 attempt

    @Column(name="faild_attempt")
    private Integer failedAttempt=0;

    @Column(name = "acc_locked")
    private Boolean accLocked=true;

    @Override
    public String toString() {
        return "SignUpDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber=" + contactNumber +
                ", alternateNumber=" + alternateNumber +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedOn=" + updatedOn +
                ", isActive=" + isActive +
                ", failedAttempt=" + failedAttempt +
                ", accLocked=" + accLocked +
                ", agree='" + agree + '\'' +
                ", passwordGenerator='" + passwordGenerator + '\'' +
                '}';
    }

    // @NotEmpty(message = "Please check agree")
    @Transient
    private String agree;

    // Autowire PasswordGenerator
    @Transient
    private String passwordGenerator;

//    public SystemDto(PasswordGenerator passwordGenerator) {
//        this.passwordGenerator = passwordGenerator.toString();
//        this.password = passwordGenerator.generatePassword(12);
//        System.out.println("Created constr for SystemDto");
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Long getAlternateNumber() {
        return alternateNumber;
    }

    public void setAlternateNumber(Long alternateNumber) {
        this.alternateNumber = alternateNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public Integer getFailedAttempt() {
        return failedAttempt;
    }

    public void setFailedAttempt(Integer failedAttempt) {
        this.failedAttempt = failedAttempt;
    }

    public Boolean isAccLocked() {
        return accLocked;
    }

    public void setAccLocked(Boolean accLocked) {
        this.accLocked = accLocked;
    }
}
