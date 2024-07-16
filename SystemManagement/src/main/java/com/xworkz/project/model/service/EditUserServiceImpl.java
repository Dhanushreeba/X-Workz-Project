package com.xworkz.project.model.service;

import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.model.repo.EditUserRepo;
import com.xworkz.project.model.repo.EditUserRepoImpl;
import com.xworkz.project.model.repo.SignUpRepoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;

@Service
public class EditUserServiceImpl implements EditUserService {

    private static final Logger log = LoggerFactory.getLogger(EditUserServiceImpl.class);
    @Autowired
    private EditUserRepo editUserRepo;

    @Autowired
    private HttpSession httpSession;

    @Autowired
            private SignUpRepoImpl signUpRepoImpl;

    EditUserServiceImpl() {
        log.info("created constr for EditUserServiceImpl");
    }

    //Edit
    @Override
    public SignUpDto findByEmail(String email) {

        return editUserRepo.findByEmail(email);
    }

    //Edit
    @Override
    public SignUpDto editByEmail(SignUpDto dto) {
        String signedInUserEmail = (String) httpSession.getAttribute("signedInUserEmail");
        if (signedInUserEmail != null) {
            SignUpDto existingUser = editUserRepo.findByEmail(signedInUserEmail);
            if (existingUser != null) {

                System.out.println("editting.......");
                existingUser.setFirstName(dto.getFirstName());
                existingUser.setLastName(dto.getLastName());
                existingUser.setContactNumber(dto.getContactNumber());
                existingUser.setAlternateNumber(dto.getAlternateNumber());
                existingUser.setAddress(dto.getAddress());

                // Set audit fields
                String updatedBy = dto.getFirstName(); // Example: Set the updatedBy field to first name for illustration
                LocalDateTime updatedOn = LocalDateTime.now(); // Set the updatedOn field to current date/time
                existingUser.setUpdatedBy(dto.getFirstName());
                existingUser.setUpdatedOn(LocalDateTime.now());
                editUserRepo.update(existingUser);


                return editUserRepo.editByEmail(existingUser); // Save the updated user details to repository
            } else {
                log.error("Existing user not found for email: {}", signedInUserEmail);
            }
        } else {
            log.error("Signed-in user email not found in session");
        }
        return null;

    }

    //to update date & time & name
    @Override
    public void setAuditValues(SignUpDto dto, String updatedBy, LocalDateTime updatedOn) {

        dto.setUpdatedBy(updatedBy);
        dto.setUpdatedOn(updatedOn);

    }

    @Override
    public SignUpDto updateUser(SignUpDto signUpDto) {
        return editUserRepo.update(signUpDto);
    }

}

//        // Retrieve the current user session's email
//        String signedInUserEmail = (String) httpSession.getAttribute("signedInUserEmail");
//        if (signedInUserEmail != null) {
//            SignUpDto existingUser = editUserRepo.findByEmail(signedInUserEmail);
//            if (existingUser != null) {
//                existingUser.setFirstName(dto.getFirstName());
//                existingUser.setLastName(dto.getLastName());
//                existingUser.setContactNumber(dto.getContactNumber());
//                existingUser.setAlternateNumber(dto.getAlternateNumber());
//                existingUser.setAddress(dto.getAddress());
//
//                // Set audit fields on existing user
//                String updatedBy = dto.getFirstName(); // Example: Set the updatedBy field to first name for illustration
//                LocalDateTime updatedOn = LocalDateTime.now(); // Set the updatedOn field to current date/time
//                setAuditValues(existingUser, updatedBy, updatedOn);
//
//                return editUserRepo.editByEmail(existingUser); // Save the updated user details to repository
//            } else {
//                log.error("Existing user not found for email: {}", signedInUserEmail);
//            }
//        } else {
//            log.error("Signed-in user email not found in session");
//        }
//        return null;// Retrieve the current user session's email