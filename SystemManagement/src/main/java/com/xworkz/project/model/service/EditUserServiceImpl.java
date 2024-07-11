package com.xworkz.project.model.service;

import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.model.repo.EditUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Service
public class EditUserServiceImpl implements EditUserService{

    @Autowired
    private EditUserRepo editUserRepo;

    @Autowired
    private HttpSession httpSession;

    //Edit
    @Override
    public SignUpDto findByEmail(String email) {

        return editUserRepo.findByEmail(email);
    }

    //Edit
    @Override
    public SignUpDto editByEmail(SignUpDto dto) {
        SignUpDto existingUser = editUserRepo.findByEmail(dto.getEmail());
        httpSession.getAttribute("signedInUserEmail");
        if (existingUser != null) {
            existingUser.setFirstName(dto.getFirstName());
            existingUser.setLastName(dto.getLastName());
            existingUser.setContactNumber(dto.getContactNumber());
            existingUser.setAlternateNumber(dto.getAlternateNumber());
            existingUser.setAddress(dto.getAddress());
            existingUser.setAgree(dto.getAgree());
            return editUserRepo.editByEmail(existingUser);// Pass the updated user details to the repo method
    }
        return null;

       }

}







