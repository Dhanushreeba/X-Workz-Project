package com.xworkz.project.model.repo;

import com.xworkz.project.dto.*;

import java.util.List;
import java.util.Optional;

public interface AdminRepo {

    //Login page
    AdminDto findByEmailAndPassword(String email, String password);

    //Admin can view all user data
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
    List<DepartmentDto> findAll(String  departmentType);

    //update status and department id
    void updateStatusAndDepartmentId(int complaintId, int departmentId, String status);


    //register department admin data save
    boolean saveDepartmentAdminData(DepartmentAdminDto departmentAdminDto);
}

