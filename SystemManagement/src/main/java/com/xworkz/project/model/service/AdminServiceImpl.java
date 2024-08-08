package com.xworkz.project.model.service;

import com.xworkz.project.dto.*;
import com.xworkz.project.model.repo.AdminRepo;
import com.xworkz.project.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public boolean findByEmailAndPassword(String email, String password) {
        System.out.println("findByEmailAndPassword method in Service Implementation");

        AdminDto data = adminRepo.findByEmailAndPassword(email, password);

        if (data != null) {
            System.out.println("findByEmailAndPassword successful in AdminServiceImpl");
            return true;
        } else {
            System.out.println("findByEmailAndPassword not successful in AdminServiceImpl");
        }
        return false;
    }

    @Override
    public List<SignUpDto> findById(SignUpDto dto) {
        System.out.println("findById method in AdminServiceImpl..");
        List<SignUpDto> dtoData=  adminRepo.findById(dto);
        if(dtoData!=null)
        {
            System.out.println("findById data successful in AdminServiceImpl..");
            return dtoData;
        }
        else
        {
            System.out.println("findById data not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }

    @Override
    public List<RaiseComplaintDto> getById(RaiseComplaintDto raiseComplaintDto) {
        System.out.println("getById method in AdminServiceImpl..");
        List<RaiseComplaintDto> raiseComplaintDtoData=  adminRepo.getById(raiseComplaintDto);
        if(raiseComplaintDtoData!=null)
        {
            System.out.println("getById data successful in AdminServiceImpl..");
            return raiseComplaintDtoData;
        }
        else
        {
            System.out.println("getById data not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }


    //search by type and city
    @Override
    public List<RaiseComplaintDto> searchByComplaintTypeAndCity(String complaintType, String city) {
        System.out.println("Running search By Type and city in Service implementation");
        List<RaiseComplaintDto> list = this.adminRepo.searchByComplaintTypeAndCity(complaintType,city);
        if (!list.isEmpty()) {
            System.out.println("Search result for complaint type And city: " + complaintType);
            return list;
        } else {
            System.out.println("No complaints found for complaint type And city : " + complaintType);
            return Collections.emptyList();
        }

    }

    @Override
    public List<RaiseComplaintDto> searchByComplaintTypeOrCity(String complaintType, String city) {
        System.out.println("searchByComplaintTypeAndCity method running in AdminServiceImpl..");

        List<RaiseComplaintDto> listOfData = adminRepo.searchByComplaintTypeOrCity(complaintType, city);
        if (!listOfData.isEmpty()) {
            System.out.println("searchComplaintTypeAndCity successful in AdminServiceImpl");
            return listOfData;
        } else {
            System.out.println("searchByComplaintTypeAndCity not successful in AdminServiceImpl..");
            return Collections.emptyList();
        }


    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        System.out.println("saveDepartment method running in AdminServiceImpl..");

        DepartmentDto data = adminRepo.saveDepartment(departmentDto);

        System.out.println("data:" + data);

        if (data != null) {
            System.out.println("saveDepartment  successful in AdminServiceImpl..");

            return data;
        } else {
            System.out.println("saveDepartment not successful in AdminServiceImpl..");
        }

        return null;
    }

    //find all Departments in dropdown where admin view details
    @Override
    public List<DepartmentDto> findAll(String departmentName) {
        System.out.println("findAll method is running in AdminServiceImpl..");
        List<DepartmentDto> data = adminRepo.findAll(departmentName);
        System.out.println("DepartmentName: " +data);

        if (data != null) {
            System.out.println("findAll successful in AdminServiceImpl..");
            return data;
        } else {
            System.out.println("findAll  not successful in AdminServiceImpl..");
        }
        return null;

    }

    //update department id and Status
    @Override
    public void updateStatusAndDepartmentId(int complaintId, int departmentId, String status) {
        //update status and department id

        System.out.println("updateStatusAndDepartmentId method running in AdminRepoImpl.. RaiseComplaintService");
        adminRepo.updateStatusAndDepartmentId(complaintId, departmentId, status);
    }

    //save department admin data
    @Override
    public boolean saveDepartmentAdminData(DepartmentAdminDto departmentAdminDto) {
        System.out.println("saveDepartmentAdminData method running in saveDepartmentAdminData in AdminServiceImpl..");

        boolean saveData= adminRepo.saveDepartmentAdminData(departmentAdminDto);

        if(saveData)
        {
            System.out.println("saveDepartmentAdminData saved successfully..");
            return  true;
        }

        else
        {
            System.out.println("saveDepartmentAdminData not saved successfully....");
        }

        return false;
    }

    @Override
    public void sendingEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("dhanushreegowda1999@gmail.com");

        mailSender.send(message);
    }

    @Override
    public DepartmentAdminDto findEmailAndPasswordAndDepartment(String email, String password,String departmentName) {
        System.out.println("findEmailAndPassword method running in AdminServiceImpl..");

        DepartmentAdminDto departmentAdminDto = adminRepo.findByEmail(email);

        if (departmentAdminDto != null) {
            System.out.println(departmentAdminDto.getPassword() + "********" + password);

            if (encoder.matches(password, departmentAdminDto.getPassword()) && departmentAdminDto.getDepartmentName().equals(departmentName)) {
                System.out.println("findEmailAndPasswordAndDepartment successful in AdminServiceImpl..");
                return departmentAdminDto;
            } else {
                System.out.println("Password mismatch in AdminServiceImpl..");
            }
        } else {
            System.out.println("No user found with email: " + email);
        }

        System.out.println("findEmailAndPasswordAndDepartment not successful in AdminServiceImpl..");
        return null;

    }

    @Override
    public DepartmentDto searchByDepartmentType(String departmentName) {
        System.out.println("Running searchByDepartmentType method in AdminServiceImpl...");
        DepartmentDto departmentDto = adminRepo.searchByDepartmentType(departmentName);
        if (departmentDto != null) {
            System.out.println("FindBy Department Name successfully: " + departmentName);
            return departmentDto;
        }
        System.out.println("No Department found for name: " + departmentName);
        return null;
    }


//    @Override
//    public List<DepartmentDto> getAllDepartment() {
//        adminRepo.getAllDepartment();
//        return Collections.emptyList();
//    }

    @Override
    public DepartmentAdminDto resetPasswordEmail(String email) {
        System.out.println("resetPasswordEmail method running in AdminServiceImpl..");

        DepartmentAdminDto resetEmail = adminRepo.resetPasswordEmail(email);
        if (resetEmail != null) {
            System.out.println("resetPasswordEmail successful in AdminServiceImpl..");

            //generate random password
            String newPassword = PasswordGenerator.generatePassword(12);


            adminRepo.updatePassword(email, encoder.encode(newPassword));
            //set new password
            resetEmail.setPassword(newPassword);
            System.out.println(resetEmail.getPassword() + "********" + newPassword);

           // mailSender.forgotPassword(resetEmail);

            // Reset failed attempts
            // adminRepo.resetFailedAttempts(email);
            //mailService.unlockAccount(email);
            //
            return resetEmail;
        } else {
            System.out.println("Email not exits..");
        }
        return null;
    }

 //   @Override
//    public void incrementFailedAttempts(String email) {
//        DepartmentAdminDto user = adminRepo.findByEmail(email);
//        if (user != null) {
//            int attempts = user.getFailedAttempt();
//            user.setFailedAttempt(attempts);
//            if (attempts >= 3) {
//                user.setAccountLocked(true);
//            }
//            adminRepo.update(user);
//        } else {
//            System.out.println("No user found with email: " + email + " when incrementing failed attempts");
//        }
//    }

    @Override
    public void incrementFailedAttempts(String email) {
        DepartmentAdminDto user = adminRepo.findByEmail(email);
        if (user != null) {
            int attempts = user.getFailedAttempt() + 1; // Increment the attempts
            user.setFailedAttempt(attempts);
            if (attempts >= 3) {
                user.setAccountLocked(true);
            }
            adminRepo.update(user);
        } else {
            System.out.println("No user found with email: " + email + " when incrementing failed attempts");
        }
    }

    @Override
    public int getFailedAttempts(String email) {
        DepartmentAdminDto user = adminRepo.findByEmail(email);
        return (user != null) ? user.getFailedAttempt() : 0;
    }

    @Override
    public void resetFailedAttempts(String email) {

        DepartmentAdminDto user = adminRepo.findByEmail(email);
        if (user != null) {
            user.setFailedAttempt(0);
            adminRepo.update(user);
        } else {
            System.out.println("No user found with email: " + email + " when resetting failed attempts");
        }
    }

    @Override
    public void lockAccount(String email) {

        DepartmentAdminDto user = adminRepo.findByEmail(email);
        if (user != null) {
            user.setAccountLocked(true);
            adminRepo.update(user);
        } else {
            System.out.println("No user found with email: " + email + " when locking account");
        }
    }

    @Override
    public void unlockAccount(String email) {

        DepartmentAdminDto user = adminRepo.findByEmail(email);
        if (user != null) {
            user.setAccountLocked(false);
            adminRepo.update(user);
        } else {
            System.out.println("No user found with email: " + email + " when unlocking account");
        }
    }

    @Override
    public boolean resetPassword(String email, String oldPassword, String newPassword, String confirmPassword) {
        System.out.println("calling resetPassword method :");
        DepartmentAdminDto departmentAdminDto = adminRepo.findByEmail(email);

        if (!adminRepo.emailExists(email)) {
            System.out.println("user not found in reset password inside 1st if :" + email);
            return false;
        }
        String storepassword = departmentAdminDto.getPassword();

        System.out.println("storedPassword:" + newPassword + "oldPassword:" + oldPassword);
        if (encoder.matches(oldPassword, storepassword)) {

            System.out.println(confirmPassword + "---------->" + newPassword);


            if (!adminRepo.verifyOldPassword(email, storepassword)) {
                System.out.println("old password not matched :" + email);
                return false;
            }
            if (!newPassword.equals(confirmPassword)) {
                return false;
            }
            //signUpRepo.updatePassword(email, newPassword);
            departmentAdminDto.setPassword(encoder.encode(newPassword));
            adminRepo.update(departmentAdminDto);
            sendPasswordEmail(email, "Password Reset Successful", "Your password has been successfully reset..Your new password is :" + newPassword);




        }else{
            System.out.println("encoder is not working........");}
        return true;
    }

    @Override
    public void sendPasswordEmail(String toEmail, String subject, String body) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom("dhanushreegowda1999@gmail.com");


        mailSender.send(simpleMailMessage);
    }

    @Override
    public List<DepartmentAdminDto> searchById(Integer id) {
        System.out.println("searchById method running in AdminServiceImpl..");

        List<DepartmentAdminDto> listOfData = adminRepo.searchById(id);
        if (!listOfData.isEmpty()) {
            System.out.println("searchById successful in AdminServiceImpl");
            return listOfData;
        } else {
            System.out.println("searchById not successful in AdminServiceImpl..");
            return Collections.emptyList();
        }
    }


//    @Override
//    public List<DepartmentAdminDto> searchByEmail(String email) {
//        System.out.println("Running search By Email in Service implementation");
//        List<DepartmentAdminDto> list = adminRepo.searchByEmail(email);
//        if (!list.isEmpty()) {
//            System.out.println("Search result for email: " + email);
//            return list;
//        } else {
//            System.out.println("No complaints found for email : " + email);
//            return Collections.emptyList();
//        }
//    }


}
