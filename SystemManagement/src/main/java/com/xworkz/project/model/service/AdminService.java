package com.xworkz.project.model.service;

import com.xworkz.project.dto.AdminDto;
import com.xworkz.project.dto.SignUpDto;

import java.util.List;

public interface AdminService {

    public boolean findByEmailAndPassword(String email,String password);

    //admin can view all user details(signUp details)

    List<SignUpDto> findById(SignUpDto dto);
}
