package com.xworkz.project.model.service;

import com.xworkz.project.dto.*;

import java.util.List;

public interface AdminService {

    public boolean findByEmailAndPassword(String email,String password);

    //admin can view all user details(signUp details)

    List<SignUpDto> findById(SignUpDto dto);

    //admin to view all complaits raised
    List<RaiseComplaintDto> getById(RaiseComplaintDto raiseComplaintDto);


    //admin search by based on complaint type And city

    List<RaiseComplaintDto> searchByComplaintTypeAndCity(String complaintType, String city);


    //admin search by complaint type or city

    List<RaiseComplaintDto> searchByComplaintTypeOrCity(String complaintType, String city);

    //to save department details
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    //find all Departments in dropdown where admin view details
    List<DepartmentDto> findAll(String departmentName) ;

    //update status and department id
    void updateStatusAndDepartmentId(int complaintId, int departmentId, String status);

    //save department admin data
    boolean saveDepartmentAdminData(DepartmentAdminDto departmentAdminDto);

    //to send an email to the particular admin entry
    public void sendingEmail(String to, String subject, String body);

    //subAdmin login id email exists in database
    public DepartmentAdminDto findEmailAndPasswordAndDepartment(String email, String password,String departmentName);


    //to reset password
    public DepartmentAdminDto resetPasswordEmail(String email);


    //checking wrong password and lock the account
    void incrementFailedAttempts(String email);

    int getFailedAttempts(String email);

    void resetFailedAttempts(String email);

    void lockAccount(String email);

    //to unlock when I new password generate
    void unlockAccount(String email);

    //Reset Password
    public boolean resetPassword(String email, String oldPassword, String newPassword, String confirmPassword);

    //send Reset password to email

    //Reset Password
    public void sendPasswordEmail(String toEmail, String subject, String body);

    //to view department name list in the Department admin Registration JSP
    //void viewDepartmentList( Long departmentId,Long departmentAdminId);
    DepartmentDto searchByDepartmentType(String departmentName);

//    List<DepartmentDto> getAllDepartment();

//    List<DepartmentAdminDto> searchByEmail(String email);

    List<DepartmentAdminDto> searchById(Integer id);



}
