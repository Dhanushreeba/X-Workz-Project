package com.xworkz.project.model.repo;

import com.xworkz.project.dto.*;

import java.util.List;
import java.util.Optional;

public interface AdminRepo {

    //Login page
    AdminDto findByEmailAndPassword(String email, String password);

    //Admin to view all user data
    List<SignUpDto> findById(SignUpDto dto);

    //Admin to view raised complaint
     List<RaiseComplaintDto> getById(RaiseComplaintDto raiseComplaintDto) ;


    //search by complaint type

    List<RaiseComplaintDto> searchByComplaintTypeAndCity(String complaintType, String city);


    //search by complaintType and city

    List<RaiseComplaintDto> searchByComplaintTypeOrCity(String complaintType, String city);

    //to save details of department
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    //find all department
    List<DepartmentDto> findAll(String  departmentName);


    //update status and department id
    void updateStatusAndDepartmentId(int complaintId, int departmentId, String status);


    //register department admin data save
    boolean saveDepartmentAdminData(DepartmentAdminDto departmentAdminDto);

    //subAdmin login id email exists in database
    DepartmentAdminDto findEmailAndPasswordAndDepartment(String email, String password,String departmentName);

    //to check email is their or not
    DepartmentAdminDto findByEmail(String email);

    //forgot password

    public DepartmentAdminDto resetPasswordEmail(String email);

    //then I have to update forgot password in database

    void updatePassword(String email, String password);

    //update account locked and attempt failed

    boolean update(DepartmentAdminDto  departmentAdminDto);

    //ResetPassword
    boolean emailExists(String email);

    //ResetPassword
    boolean verifyOldPassword(String email, String oldPassword);

    //ResetPassword
    void updatedPassword(String email, String newPassword);

    //to view department name list in the Department admin Registration JSP
    // void viewDepartmentList(Long departmentId,Long departmentAdminId);
    DepartmentDto searchByDepartmentType(String departmentName);

//    List<DepartmentDto> getAllDepartment();

//    List<DepartmentAdminDto> searchByEmail(String email);

     List<DepartmentAdminDto> searchById(Integer id);

}

