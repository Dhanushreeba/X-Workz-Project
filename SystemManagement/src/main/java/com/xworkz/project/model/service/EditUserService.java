package com.xworkz.project.model.service;


import com.xworkz.project.dto.SignUpDto;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public interface EditUserService {

    //Edit
    SignUpDto findByEmail(String email);

    //Edit
    SignUpDto editByEmail(SignUpDto dto);

    void setAuditValues(SignUpDto dto, String updatedBy, LocalDateTime updatedOn);

    SignUpDto updateUser(SignUpDto signUpDto);
}

