package com.xworkz.project.model.repo;

import com.xworkz.project.dto.SignUpDto;

public interface EditUserRepo {

    //Edit
    SignUpDto findByEmail(String email);

    //Edit
    SignUpDto editByEmail(SignUpDto updatedUserDetails);

}

