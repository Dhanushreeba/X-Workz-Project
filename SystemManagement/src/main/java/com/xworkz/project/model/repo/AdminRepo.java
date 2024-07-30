package com.xworkz.project.model.repo;

import com.xworkz.project.dto.AdminDto;
import com.xworkz.project.dto.RaiseComplaintDto;
import com.xworkz.project.dto.SignUpDto;

import java.util.List;
import java.util.Optional;

public interface AdminRepo {

    //Login page
    AdminDto findByEmailAndPassword(String email, String password);

    //Admin can view all user data
    List<SignUpDto> findById(SignUpDto dto);

    //Admin to view raised complaint
     List<RaiseComplaintDto> getById(RaiseComplaintDto raiseComplaintDto) ;

//     //search by city
//     List<RaiseComplaintDto> searchByCity(String city);
//
//    //Search by Type
//    List<RaiseComplaintDto> searchByType(String complaintType);
//
////     //Search by TypeAndCity
//    List<RaiseComplaintDto> searchByTypeAndCity(String complaintType, String city);
//
//    //search by complaintType and city
//    List<RaiseComplaintDto> searchByComplaintTypeOrCity(String complaintType, String city);


    //search by complaint type

    List<RaiseComplaintDto> searchByComplaintTypeAndCity(String complaintType, String city);


    //search by complaintType and city

    List<RaiseComplaintDto> searchByComplaintTypeOrCity(String complaintType, String city);

}

