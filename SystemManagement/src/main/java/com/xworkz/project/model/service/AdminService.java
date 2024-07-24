package com.xworkz.project.model.service;

import com.xworkz.project.dto.AdminDto;
import com.xworkz.project.dto.RaiseComplaintDto;
import com.xworkz.project.dto.SignUpDto;

import java.util.List;

public interface AdminService {

    public boolean findByEmailAndPassword(String email,String password);

    //admin can view all user details(signUp details)

    List<SignUpDto> findById(SignUpDto dto);

    //admin to view all complaits raised
    List<RaiseComplaintDto> getById(RaiseComplaintDto raiseComplaintDto);

    //search operation to search by Type
    List<RaiseComplaintDto> searchByType(String complaintType);

    //search operation to search by City
    List<RaiseComplaintDto> searchByCity(String city);

    //search operation to search by TypeAndCity
    List<RaiseComplaintDto> searchByTypeAndCity(String complaintType, String city);


}
