package com.xworkz.project.model.repo;

import com.xworkz.project.dto.SignUpDto;

public interface SignUpRepo {

    // Save and validate the DTO
    boolean saveAndValidation(SignUpDto dto);

    //to generate password
    // Find a SystemDto by email and password
    SignUpDto findByEmailAndPassword(String email, String password);


    SignUpDto findByEmail(String email); // Add this method to find a user by email

    //lock
    boolean update(SignUpDto signUpDto);

    //ajax
    SignUpDto existsByEmail(String email);

    //ajax used
    SignUpDto existsByContactNumber(Long contactNumber);

    //ajax
    //to generate password
    //after you forgot password when you click on forgetPassword
    SignUpDto lookByEmail(String email);


    //ResetPassword
    boolean emailExists(String email);

    //ResetPassword
    boolean verifyOldPassword(String email, String oldPassword);

    //ResetPassword
    void updatePassword(String email, String newPassword);

    //Veiw
    SignUpDto getUserByEmail(String email);


}
