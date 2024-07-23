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



}

