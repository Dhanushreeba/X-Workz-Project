package com.xworkz.project.model.service;

import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.model.repo.SignUpRepo;
import com.xworkz.project.util.PasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SignUpServiceImpl implements SignUpService {

    private static final Logger log = LoggerFactory.getLogger(SignUpServiceImpl.class);
    @Autowired
    private SignUpRepo signUpRepo;


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Autowired
    private PasswordEncoder encoder;

    //view
    @Autowired
    private HttpSession httpSession;

    //password lock
    private Map<String, Integer> failedAttemptsMap = new HashMap<>();
    private Map<String, SignUpDto> users = new HashMap<>(); // Simulated database

   // private static final Logger logger = Logger.getLogger(SystemServiceImpl.class.getName());


    public SignUpServiceImpl(){
        SignUpServiceImpl.log.info("running constr for SignUpServiceImpl class");
    }

    @Override
    public boolean save(SignUpDto dto) {

//        if (emailExists(dto.getEmail())) {
//            System.out.println("Email " + dto.getEmail() + " already exists.");
//            return false;
//        }

        log.info("save method from SignUpServiceImpl");

        setAuditValues(dto,dto.getFirstName(),LocalDateTime.now(),dto.getFirstName(),LocalDateTime.now(),true);

        boolean save= signUpRepo.saveAndValidation(dto);
        if (save){
            log.info("Repo save in search successfull "+dto);
        }else{
            log.info("Repo save in search not successfull "+dto);
        }
        users.put(dto.getEmail(), dto); // Simulate saving to a database//password locking
        return true;
    }

    @Override
    public void setAuditValues(SignUpDto dto, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isActive)
    {
        dto.setCreatedBy(createdBy);
        dto.setCreatedOn(createdOn);
        dto.setUpdatedBy(updatedBy);
        dto.setUpdatedOn(updatedOn);
        dto.setActive(isActive);
    }

//    @Override
//    public SignUpDto findByEmailAndPassword(String email, String password) {
//        //password lock statements
//        SignUpDto user= signUpRepo.findByEmailAndPassword(email, password);
//        if (user!=null && !user.isAccLocked()&& user.getPassword().equals(password)){
//            return user;
//        }
//
//        return null;
//    }

//    public SignUpDto findByEmailAndPassword(String email, String password) {
//        // Line 71: this is where the NullPointerException occurs
//        SignUpDto user = signUpRepo.findByEmailAndPassword(email, password);
//        // Other logic
//        return user;
//    }

    @Override
    public SignUpDto findByEmailAndPassword(String email, String password) {
        SignUpDto dto=signUpRepo.findByEmail(email);

        // Make sure email and password are not null
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and Password cannot be null");
        }

        //to send encrypted password for database and decrypted password for email
        if (dto!=null && encoder.matches(password,dto.getPassword())) {
            System.out.println("data exists :"+dto);
            return dto;
        }

        // Ensure the repository is not null
        if (signUpRepo == null) {
            throw new IllegalStateException("SignUpRepo has not been initialized");
        }

        SignUpDto user = signUpRepo.findByEmailAndPassword(email, password);
        return user;
    }

    @Override
    public SignUpDto findByEmail(String email) {
        log.info("Running findByEmail in service");

        SignUpDto user=users.get(email);
        if (user !=null && !user.isAccLocked()){
            return user;
        }
        return signUpRepo.findByEmail(email);
    }


    //   private Map<String, SystemDto> users = new HashMap<>();



    //send email to get otp
    @Override
    public void sendingEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("dhanushreegowda1999@gmail.com");

        mailSender.send(message);
    }

    //account lock after 3 attempts
    @Override
    public void incrementFailedAttempts(String email) {
        SignUpDto user = signUpRepo.findByEmail(email);
        if (user != null) {
            int attempts = user.getFailedAttempt() + 1;
            user.setFailedAttempt(attempts);
            if (attempts >= 3) {
                user.setAccLocked(true);
            }
            signUpRepo.update(user);
        }

    }

    @Override
    public int getFailedAttempts(String email) {
        SignUpDto user = signUpRepo.findByEmail(email);
        return (user != null) ? user.getFailedAttempt() : 0;
//        return failedAttemptsMap.getOrDefault(email, 0);
    }

    @Override
    public void resetFailedAttempts(String email) {
        SignUpDto user = signUpRepo.findByEmail(email);
        if (user != null) {
            user.setFailedAttempt(0);//false
            signUpRepo.update(user);
        }

    }

    @Override
    public void lockAccount(String email) {

        SignUpDto user = signUpRepo.findByEmail(email);
        if (user != null) {
            user.setAccLocked(true);
            signUpRepo.update(user);
        }
    }

    @Override
    public void unlockAccount(String email){
        SignUpDto user=signUpRepo.findByEmail(email);
        if (user!=null){
            user.setAccLocked(false);
            signUpRepo.update(user);
        }
    }




//    @Override
//    public boolean existsByEmail(String email) {
//        return systemRepo.existsByEmail(email);
//    }

    @Override
    public boolean existsByEmail(String email) {
        System.out.println("Email:"+email);
        SignUpDto dto = signUpRepo.existsByEmail(email);
        if (dto != null) {
            return true;
        } else {
            return false;
        }

    }
//    @Override
//    public boolean existsByContactNumber(Long contactNumber) {
//        System.out.println("contactNumber:"+contactNumber);
//        SignUpDto dto = signUpRepo.existsByContactNumber(contactNumber);
//        if (dto != null) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    @Override
    public boolean existsByContactNumber(Long contactNumber) {
        log.info("contactNumber:" + contactNumber);
        SignUpDto dto = signUpRepo.existsByContactNumber(contactNumber);
        return dto != null;
    }

//    @Override
//    public SignUpDto lookByEmail(String email) {
//        if (email == null) {
//            throw new IllegalArgumentException("Email cannot be null");
//        }
//
//        // Ensure the repository is not null
//        if (signUpRepo == null) {
//            throw new IllegalStateException("SignUpRepo has not been initialized");
//        }
//
//        SignUpDto user = signUpRepo.lookByEmail(email);
//        return user;
//    }

    @Override
    public boolean lookByEmail(String email) {
        log.info("Email:" + email);
        SignUpDto dto = signUpRepo.lookByEmail(email);
        if (dto != null) {
            return true;
        } else {
            return false;
        }
    }


    //ResetPassword
    @Override
    public boolean resetPassword(String email, String oldPassword, String newPassword, String confirmPassword) {
        if (!signUpRepo.emailExists(email))
        {
            return false;
        }

        if (!signUpRepo.verifyOldPassword(email, oldPassword))
        {
            return false;
        }
        if (!newPassword.equals(confirmPassword))
        {
            return false;
        }
        signUpRepo.updatePassword(email, newPassword);
        sendPasswordEmail(email, "Password Reset Successful", "Your password has been successfully reset..Your new password is :" +newPassword);

        return true;
    }

    //ResetPassword
    @Override
    public void sendPasswordEmail(String toEmail, String subject, String body) {


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom("dhanushreegowda1999.com");


        mailSender.send(simpleMailMessage);
    }

    //view
    @Override
    public SignUpDto getUserByEmail(String email) {
        return signUpRepo.findByEmail(email);

    }

    //view
    @Override
    public String getSignedInUserEmail() {

        httpSession.getAttribute("signedInUserEmail");
        return "String";
    }
}

