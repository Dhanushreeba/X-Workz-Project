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
    public List<DepartmentDto> findAll(String departmentType) {
        System.out.println("findAll method is running in AdminServiceImpl..");
        List<DepartmentDto> data = adminRepo.findAll(departmentType);
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
    public DepartmentAdminDto findEmailAndPassword(String email, String password) {
        System.out.println("findEmailAndPassword method running in AdminServiceImpl..");

        DepartmentAdminDto departmentAdminDto = adminRepo.findByEmail(email);
        if (departmentAdminDto != null) {
            System.out.println(departmentAdminDto.getPassword() + "********" + password);

            if (encoder.matches(password, departmentAdminDto.getPassword())) {
                System.out.println("findEmailAndPassword successful in AdminServiceImpl..");
                return departmentAdminDto;
            } else {
                System.out.println("Password mismatch in AdminServiceImpl..");
            }
        } else {
            System.out.println("No user found with email: " + email);
        }

        System.out.println("findEmailAndPassword not successful in AdminServiceImpl..");
        return null;

    }

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

}
