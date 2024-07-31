package com.xworkz.project.model.service;

import com.xworkz.project.dto.AdminDto;
import com.xworkz.project.dto.DepartmentDto;
import com.xworkz.project.dto.RaiseComplaintDto;
import com.xworkz.project.dto.SignUpDto;

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
    List<DepartmentDto> findAll(String departmentType) ;

    //update status and department id
    void updateStatusAndDepartmentId(int complaintId, int departmentId, String status);

}
