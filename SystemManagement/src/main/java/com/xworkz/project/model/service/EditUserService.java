package com.xworkz.project.model.service;


import com.xworkz.project.dto.SignUpDto;
import org.springframework.web.multipart.MultipartFile;

public interface EditUserService {

    //Edit
    SignUpDto findByEmail(String email);

    //Edit
    SignUpDto editByEmail(SignUpDto dto);



}
