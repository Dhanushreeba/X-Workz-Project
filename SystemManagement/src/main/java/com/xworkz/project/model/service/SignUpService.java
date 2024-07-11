package com.xworkz.project.model.service;

import com.xworkz.project.dto.SignUpDto;


import java.time.LocalDateTime;

public interface SignUpService {

    public boolean save(SignUpDto dto);

//    public LocalDateTime currentDateTime(SystemDto dto);

 //  public void setCurrentDateTime(SystemDto dto, String dateTimeStr, String format);

    void setAuditValues(SignUpDto dto, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isActive);

    //to auto generate password
    SignUpDto findByEmailAndPassword(String email, String password);

    public SignUpDto findByEmail(String email);

  //  public String generateRandomPassword();

    public void sendingEmail(String to, String subject, String body);

    //checking wrong password and lock the account


    void incrementFailedAttempts(String email);

    int getFailedAttempts(String email);

    void resetFailedAttempts(String email);

    void lockAccount(String email);

    void unlockAccount(String email);

//ajax
    boolean existsByEmail(String email);
//ajax
    boolean existsByContactNumber(Long contactNumber);

    //to generate password after you forgot password
    boolean lookByEmail(String email);

    //Reset Password
    public boolean resetPassword(String email, String oldPassword, String newPassword, String confirmPassword);

    //send Reset password to email

    //Reset Password
    public void sendPasswordEmail(String toEmail, String subject, String body);

    //view
    SignUpDto getUserByEmail(String email);

    //view
    String getSignedInUserEmail();


}


